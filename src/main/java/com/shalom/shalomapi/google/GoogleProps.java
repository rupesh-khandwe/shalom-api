package com.shalom.shalomapi.google;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
@Configuration
public class GoogleProps {
	/**
	 * Google client id.
	 */
	@Value("${google.clientId}")
	private String clientId;
}
