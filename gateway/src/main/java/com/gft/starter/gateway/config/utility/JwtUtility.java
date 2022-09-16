package com.gft.starter.gateway.config.utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtility {
	
	private String secretKey = "secretkey123";

	public Claims getClaims(final String token) {
		try {
			System.out.println(token);
			Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			System.out.println();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + ">" + e);
		}
		return null;
	}
	
	
	public boolean isInvalid(final String token) throws Exception{
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (Exception e) {
        	throw new Exception("Problem with Token", e);
        }
        return true;
	}

}

