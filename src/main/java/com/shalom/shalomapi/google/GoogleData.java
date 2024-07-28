package com.shalom.shalomapi.google;

import lombok.Value;

@Value
public class GoogleData {
	private String googleId;
	private String email;
	private boolean emailVerified;
	private String name;
	private String avatarUrl;
	private String locale;
}
