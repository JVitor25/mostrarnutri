package com.gft.starter.gateway.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.gft.starter.gateway.config.utility.JwtUtility;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter  {

	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private RouterValidator routerValidator;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();

		if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            final String token = this.getAuthHeader(request);
            
            try {
				if (jwtUtility.isInvalid(token))
				    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
		
       
	}
	 /*PRIVATE*/
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }
    
    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtility.getClaims(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
}
