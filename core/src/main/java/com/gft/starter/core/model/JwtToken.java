package com.gft.starter.core.model;

public class JwtToken {
	private String jwtToken;

	public JwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
