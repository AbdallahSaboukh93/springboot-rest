package com.restfullProject.restfullProject.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.restfullProject.restfullProject.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utilits {
	private final Random random = new SecureRandom();
	private final String alfabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String generateUserId(int length) {
	 return generateRandomString(length);
 }
	public String generateAddressId(int length) {
		 return generateRandomString(length);
	 }


	private String generateRandomString(int length) {

		StringBuilder returnedStringBuilder = new StringBuilder(length);
		for (int i=0 ;i < length; i++) {
			returnedStringBuilder.append(alfabet.charAt(random.nextInt(alfabet.length()))) ;
		}
		return new String(returnedStringBuilder);
	}
	

	public static boolean hasTokenExpired(String token) {
		boolean returnValue = false;

		try {
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
					.getBody();

			Date tokenExpirationDate = claims.getExpiration();
			Date todayDate = new Date();

			returnValue = tokenExpirationDate.before(todayDate);
		} catch (ExpiredJwtException ex) {
			returnValue = true;
		}

		return returnValue;
	}

    public String generateEmailVerificationToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }
    
    public String generatePasswordResetToken(String userId)
    {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }
}
