package com.quiz.mypackage.Dto;

import java.util.List;

public class LoginResponse {
	private String jwtToken;
	private List<String> roles;
	private String username;
	
	
	public LoginResponse() {}	
	public LoginResponse(String jwtToken,  List<String> roles, String username) {
		super();
		this.jwtToken = jwtToken;
		this.roles = roles;
		this.username = username;
	}

	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
