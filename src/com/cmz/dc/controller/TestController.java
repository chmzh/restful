package com.cmz.dc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.cmz.dc.groovy.BookingService;

@Controller
public class TestController implements ServletContextAware {
	
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
}
