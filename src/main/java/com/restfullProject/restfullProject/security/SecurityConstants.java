package com.restfullProject.restfullProject.security;

import org.springframework.context.ApplicationContext;

import com.restfullProject.restfullProject.SpringApplicationContext;

public class SecurityConstants {
	public static final String SIGN_UP_URL = "/users";
	public static final long EXPIRATION_TIME = 864000000;
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEARDER_STRING = "Authorization";
	public static final String REST_PASSWORD = "/users/password-reset";
	public static final String H2_CONSOLE = "/h2-console/**";

public static  String getTokenSecret() {
	AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("AppProperties") ;
		return	appProperties.getTokenSecret();
}
}
