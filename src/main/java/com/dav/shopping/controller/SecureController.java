package com.dav.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dav.shopping.entity.User;
import com.dav.shopping.service.UserService;

@RestController
@RequestMapping("/secure")
public class SecureController {

	@Autowired
	private UserService userService;

	//@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String helloUser() {
		return "hello user";
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String helloAdmin() {
		return "hello admin";
	}

	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "client", method = RequestMethod.GET)
	public String helloClient() {
		return "hello user authenticated by normal client";
	}

	@RequestMapping("/user/users")
	public String loginSuccess() {
		return "Login Successful!";
	}

	@RequestMapping(value = "/user/email", method = RequestMethod.POST)
	public User findByEmail(@RequestBody String email) {
		System.out.println(email);
		return userService.findByEmail(email);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		return userService.save(user);
	}
}
