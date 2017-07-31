package com.dav.shopping.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dav.shopping.config.TokenHandler;
import com.dav.shopping.entity.User;
import com.dav.shopping.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenHandler tokenHandler;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void registerUser(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.save(user);
		return ;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(@RequestBody User login) throws ServletException {

		String jwtToken = "";
		System.out.println(login.toString());
		if (login.getEmail() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String email = login.getEmail();
		String password = login.getPassword();

		User user = userService.findByEmail(email);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		String pwd = user.getPassword();

		if (!bCryptPasswordEncoder.matches(password, pwd)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = /*Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();*/
				tokenHandler.createTokenForUser(user);
		
		Map<String, String> data = new HashMap();
		
		data.put("id", user.getUserId()+"");
		data.put("access_token", "Bearer " +jwtToken);
		data.put("email", user.getEmail());
		data.put("username", user.getLastName());
		data.put("fullname", user.getFirstName()+ " " + user.getLastName());
		data.put("avatar", user.getAvatar());
		
		return data;
	}
}
