package com.last.project4_memerealm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class JwtHelper {

	private static final String secret = "dolor amet, cursus nec consectetur ipsum Aenean tristique, ante Lorem eu, mollis nunc sit id nunc. lacus elit. tortor Fusce adipiscing";

	/**
	 * Generate JWT token with subject is username
	 * @param subject
	 * @return token or null if exception occur
	 */
	public static String create(String subject, List<String> roles, int userId ){
		try{
			Algorithm alg = Algorithm.HMAC512(secret);

			Instant now = Instant.now();
			Instant expirationTime = now.plus(30, ChronoUnit.DAYS);
			Date expirationDate = Date.from(expirationTime);

			return JWT.create()
					.withSubject(subject)
					.withExpiresAt(expirationDate)
					.withClaim("roles", roles)
					.withClaim("userId", userId)
					.sign(alg);
		}catch (JWTCreationException ex){
			return null;
		}
	}

	/**
	 * Check if token is valid and not expired
	 * @param token
	 * @return boolean
	 */
	public static boolean verify(String token){
		DecodedJWT decoded = decodeToken(token);
		return decoded != null && isExpired(decoded);
	}

	/**
	 * Extract username from token
	 * @param token
	 * @return username|null
	 */
	public static String getUsernameFormToken(String token){
		DecodedJWT decoded = decodeToken(token);
		if(decoded != null)
			return decoded.getSubject();
		return null;
	}

	public static List<String> getRolesfromToken(String token){
		DecodedJWT decoded = decodeToken(token);
		if(decoded != null)
			return decoded.getClaim("roles").asList(String.class);
		return null;
	}

	/**
	 * Decode token to DecodedJWT object
	 * @param token
	 * @return DecodedJWT
	 */
	private static DecodedJWT decodeToken(String token){
		try{
			Algorithm alg = Algorithm.HMAC512(secret);
			JWTVerifier verifier = JWT.require(alg).build();

			return verifier.verify(token);
		}catch (JWTDecodeException ex){
			return null;
		}
	}

	/**
	 * Check if token is expired
	 * @param decodedToken
	 * @return boolean
	 */
	private static boolean isExpired(DecodedJWT decodedToken){
		Date now    = new Date();
		Date expire = decodedToken.getExpiresAt();

		return expire != null && expire.before(now);
	}
}
