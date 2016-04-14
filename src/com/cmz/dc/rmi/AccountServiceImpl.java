package com.cmz.dc.rmi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.cmz.dc.util.HttpUtils;

public class AccountServiceImpl implements AccountService {
	public void insertAccount(Account acc) { // do something...
		System.out.println("insertAccount");
	}

	public List<Account> getAccounts(String name) { // do something...
		System.out.println("getAccounts");
		return new ArrayList<Account>();
	}
	
	public static void main(String[] args) {
		//HttpUtils.post("http://localhost:8080/dc/AccountService","");
		HessianProxyFactoryBean proxyFactoryBean = new HessianProxyFactoryBean();
		proxyFactoryBean.setServiceUrl("http://localhost:8080/dc/AccountService");
		proxyFactoryBean.setServiceInterface(AccountService.class);
		
	}
}
