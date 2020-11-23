package com.example.bookstoreapi.security;

import static com.example.bookstoreapi.security.SecurityConstants.CLAIMS_ROLE;
import static com.example.bookstoreapi.security.SecurityConstants.HEADER_AUTHORIZATION;
import static com.example.bookstoreapi.security.SecurityConstants.SECRET_KEY;
import static com.example.bookstoreapi.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.bookstoreapi.exception.UnauthorizeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	// /product (1) no token -> 401(SpringBootSecurity)
	// /product (2) with Bearer JWTtoken -> JWTToken are true -> doFilterInternal

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
		// token are: "Bearer token"
		// check for config security or
		if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);

	}

	// if return null-> token not pass
	private UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
		// replace from ... token to token
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
					.getBody();

			// username in subject claims
			String username = claims.getSubject();
			if (username == null) {
				return null;
			}
			// get object
			ArrayList<String> roles = (ArrayList<String>) claims.get(CLAIMS_ROLE);
			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			if (roles != null) {
				for (String role : roles) {
					grantedAuthorities.add(new SimpleGrantedAuthority(role));
				}
			}
			return new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
		} catch (Exception e) {
			throw new UnauthorizeException(jwt);
		}

	}

}
