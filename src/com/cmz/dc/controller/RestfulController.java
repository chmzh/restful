package com.cmz.dc.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmz.dc.domain.User;

@RestController
public class RestfulController {
	@RequestMapping(method = RequestMethod.GET,value="/test/{id}")
	public User test(@PathVariable(value="id")int id){
		
		User user = new User();
		user.setId(id);
		user.setName("name");
		
		return user;
	}
}
