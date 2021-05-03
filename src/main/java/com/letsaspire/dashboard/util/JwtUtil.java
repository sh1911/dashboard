package com.letsaspire.dashboard.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
		private String secret= "bloodsbrother";
		
	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {	
	        return extractClaim(token, Claims::getExpiration);
	    }
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    
	    private Claims  extractAllClaims(String token)
		{
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}
	    
		private Boolean isTokenExpired(String token)
		{
			return extractExpiration(token).before(new Date());
		}
		
		public String generateToken(String email)
		{
			Map<String,Object> claims=new HashMap<>();
			return createToken(claims,email);
		}
		public String generateToken(String email,Map<String,Object> parameterClaims)
		{
			Map<String,Object> claims=new HashMap<>();
			claims  = parameterClaims;
			return createToken(claims,email);
		}
		
		private String createToken(Map<String, Object> claims, String email) {
			
			return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
					.signWith(SignatureAlgorithm.HS256,secret).compact();
		}
		
		public Boolean validateToken(String token,UserDetails userDetails)
		{
			final String email= extractUsername(token);
			return (email.equals(userDetails.getUsername()) && !isTokenExpired(token) );
			
		}
		public Claim getClaim(String claimKey,String token) {
		    try {
		        DecodedJWT jwt = JWT.decode(token);
		        return jwt.getClaim(claimKey);
		    } catch (JWTVerificationException ex) {
		        throw new RuntimeException(ex);
		    }
		}

}
