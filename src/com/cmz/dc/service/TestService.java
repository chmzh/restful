package com.cmz.dc.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Cacheable("test")
	public String get(){
		return "abc";
	}
}
