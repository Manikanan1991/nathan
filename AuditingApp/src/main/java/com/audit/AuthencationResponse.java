package com.audit;

public class AuthencationResponse {

	private final String jwt;

	public AuthencationResponse(String jwt) {
		
		this.jwt = jwt;
	}

	
	public String getJwt() {
		return jwt;
	}

}
