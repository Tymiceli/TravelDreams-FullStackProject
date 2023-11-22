package com.traveldreams.service;


import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	/**
	 * 
	 * What this class must do:
	 * 0. Create a JWT signing key
	 * 1. Create / generate the JWT
	 * 2. Extract claims (get stuff from the payload)
	 * 3. Verify the JWT is valid
	 * 4. Sign the JWT
	 * 
	 */

	
	@Value("${JWT_SIGNING_KEY}")
	private String jwtSigningKey;
	
	@Value("${jwt.expirationTimeInMillis}")
	private Long expirationTimeInMillis;
	
	public Claims extractAllClaims (String token) {
		
		Claims body = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
		
		return body;
	}
	
	public String getSubject (String token) {
		String subject = extractClaim(token, Claims::getSubject);
		return subject;
	}
	
	public Boolean isValidToken (String token, UserDetails user) {
			String subject = getSubject(token);
			Date expirationDate = extractClaim(token, Claims::getExpiration);
		
		return user.getUsername().equalsIgnoreCase(subject) && new Date().before(expirationDate);
	}
	
	private <T> T extractClaim (String token, Function<Claims, T> claimsExtract) {
		
		Claims allClaims = extractAllClaims(token);
		return claimsExtract.apply(allClaims);
	}
	
	public String generateToken(Map<String, Object> extraClaims, UserDetails user) {

		String jwt = Jwts.builder()
				.claims(extraClaims)
				.issuer("Tyler Miceli")
				.subject(user.getUsername())
				.header().type("JWT").and()
			    .expiration(new Date(System.currentTimeMillis() + expirationTimeInMillis)) //a java.util.Date
			    .issuedAt(new Date())
			    .signWith(getSigningKey())
			    .compact();
		
        return jwt;
	}

	private SecretKey getSigningKey() {
		
		byte[] jwtSigningKeyAsBytes = Decoders.BASE64.decode(jwtSigningKey);

        return Keys.hmacShaKeyFor(jwtSigningKeyAsBytes);
	}
	
	
	// These methods are strictly used for testing purposes
	public void setJwtSigningKey(String jwtSigningKey) {
		if (this.jwtSigningKey == null) {
			this.jwtSigningKey = jwtSigningKey;
		}
	}

	public void setExpirationTimeInMillis(Long expirationTimeInMillis) {
		if (this.expirationTimeInMillis == null) {
		this.expirationTimeInMillis = expirationTimeInMillis;
		}
	}
	
	
}
	
