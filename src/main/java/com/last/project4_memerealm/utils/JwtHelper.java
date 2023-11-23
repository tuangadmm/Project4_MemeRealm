package com.last.project4_memerealm.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

public class JwtHelper {

	@Value("${jwt.secret}")
	private static String secret;

	@Value("${jwt.expire}")
	private static long expire;

//	public String generateJwtToken(Authentication authentication) {
//
//		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//
//		return Jwts.builder()
//				.setSubject((userPrincipal.getUsername()))
//				.setIssuedAt(new Date())
//				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//				.signWith(key(), SignatureAlgorithm.HS256)
//				.compact();
//	}
//
//	private Key key() {
//		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//	}
//
//	public String getUserNameFromJwtToken(String token) {
//		return Jwts.parserBuilder().setSigningKey(key()).build()
//				.parseClaimsJws(token).getBody().getSubject();
//	}
//
//	public boolean validateJwtToken(String authToken) {
//		try {
//			Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
//			return true;
//		} catch (MalformedJwtException e) {
//			logger.error("Invalid JWT token: {}", e.getMessage());
//		} catch (ExpiredJwtException e) {
//			logger.error("JWT token is expired: {}", e.getMessage());
//		} catch (UnsupportedJwtException e) {
//			logger.error("JWT token is unsupported: {}", e.getMessage());
//		} catch (IllegalArgumentException e) {
//			logger.error("JWT claims string is empty: {}", e.getMessage());
//		}
//
//		return false;
//	}
//
//	public String getUsernameFormHttpRequest(HttpServletRequest request) {
//		String headerAuth = request.getHeader("Authorization");
//		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//			String token =  headerAuth.substring(7);
//			return getUserNameFromJwtToken(token);
//		}
//		return null;
//	}


}
