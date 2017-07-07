package com.dav.shopping.config;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.User;
import com.dav.shopping.service.UserService;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("tokenHandler")
public class TokenHandler {

	private final String secret= "DAV_SERVER";
	
	public String parseUserFromToken(String token) {
		System.out.println(token);
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return username;
	}

	public String createTokenForUser(User user) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(1l));
		
		return Jwts.builder()
				.setId(UUID.randomUUID().toString())
				.setSubject(user.getEmail())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
}
