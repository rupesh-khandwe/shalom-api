package com.shalom.shalomapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;
	private Long userId;
	private String userFirstName;
	private String userLastName;

	public JwtResponse(String jwttoken, Long userId, String userFirstName, String userLastName) {
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
	}

	@Override
	public String toString() {
		return "JwtResponse{" +
				"jwttoken='" + jwttoken + '\'' +
				", userId=" + userId +
				", userFirstName='" + userFirstName + '\'' +
				", userLastName='" + userLastName + '\'' +
				'}';
	}
}