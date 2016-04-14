package com.cmz.dc.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	public static void post(String url,String parameters){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost method = new HttpPost(url);
		try {
			
			method.addHeader("Content-type",
					"application/json; charset=utf-8");
			method.setHeader("Accept", "application/json");
			method.setEntity(new StringEntity(parameters, Charset
					.forName("UTF-8")));
			
			CloseableHttpResponse response = httpClient.execute(method);
			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity , "utf-8").trim();
			System.out.println(respContent);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
