package com.cp.authentication.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	@Value("${jwt.secret.key}")
	private String secretKey;

	public String generateJWTToken(String userName) {

		return Jwts.builder().claim("username", userName).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes())).compact();

	}

	public String extractUserName(String token) {

		return getClaims(token).get("username").toString();

	}

	public boolean validateToken(String token, String username) {

		return (username.equalsIgnoreCase(extractUserName(token)) && isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {

		return getClaims(token).getExpiration().before(new Date());
	}

	public Claims getClaims(String token) {

		return Jwts.parser().decryptWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseSignedClaims(token)
				.getPayload();

	}
}
