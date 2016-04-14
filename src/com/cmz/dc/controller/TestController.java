package com.cmz.dc.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.cmz.dc.groovy.BookingService;

@Controller
public class TestController implements ServletContextAware {
	
	
    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();
    static {
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
    }
	
	private ServletContext context;
	@Autowired
	private BookingService bookingService;
	@RequestMapping("/booking/{name}")
	public String test(@PathVariable String name) throws ServletException, IOException{
		
		return "forward:/hotels/1/hi/"+name;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
	}
	
	
	@RequestMapping("/hi/{name}")
	@ResponseBody
	public String test1(@PathVariable String name) throws ServletException, IOException{
		
		return "你好:"+name+bookingService.processBooking();
	}
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
    @RequestMapping("/code")
    public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	cs.setFilterFactory(new DoubleRippleFilterFactory());
//        switch (random.nextInt(5)) {
//            case 0:
//                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
//                break;
//            case 1:
//                cs.setFilterFactory(new MarbleRippleFilterFactory());
//                break;
//            case 2:
//                cs.setFilterFactory(new DoubleRippleFilterFactory());
//                break;
//            case 3:
//                cs.setFilterFactory(new WobbleRippleFilterFactory());
//                break;
//            case 4:
//                cs.setFilterFactory(new DiffuseRippleFilterFactory());
//                break;
//        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        session.setAttribute("captchaToken", token);
        System.out.println("当前的SessionID=" + session.getId() + "，验证码=" + token);
    }
    
    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
}
