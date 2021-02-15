package com.ebi.assignment.task.ebiassignmenttask.security.models;

public class AuthenticationResponse {

	private String jwt;
	
	public AuthenticationResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
