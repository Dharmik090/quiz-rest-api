package com.quiz.mypackage.Jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain filterChain) 
				throws ServletException, IOException{
		
		logger.debug("AuthTokenFilter called for URI: {}", req.getRequestURI());
		
		try {
			String jwt = parseJwt(req);
			if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userDetails, null, 
								userDetails.getAuthorities());
				
				logger.debug("ROles from JWT: {}", userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		catch(Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		
		filterChain.doFilter(req,res);
	}
	
	private String parseJwt(HttpServletRequest req) {
		String jwt = jwtUtils.getJwtFromHeader(req);
		logger.debug("AuthTokenFilter.java: {}", jwt);
		return jwt;
	}
}
