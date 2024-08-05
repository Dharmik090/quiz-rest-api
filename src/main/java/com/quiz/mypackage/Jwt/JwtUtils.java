package com.quiz.mypackage.Jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	private String jwtSecret;
	
	private int jwtExpirationMs;
	
	public JwtUtils() {
		this.jwtSecret = "mysecretKey123456789itcanbeanythingsoitispossibletowriteanything";
		this.jwtExpirationMs = 24*60*60*1000;
	}
	
	
	public String getJwtFromHeader(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		logger.debug("Authorization Header: {}", bearerToken);
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
	
	public String generateTokenFromUsername(UserDetails userDetails) {
		String username = userDetails.getUsername();
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key())
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
			return true;
		}
		catch(MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		}
		catch(ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		}
		catch(UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		}
		catch(IllegalArgumentException e){
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		
		return false;
	}
	
}

