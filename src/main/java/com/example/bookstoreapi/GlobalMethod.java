package com.example.bookstoreapi;

import static com.example.bookstoreapi.security.SecurityConstants.SECRET_KEY;

import javax.xml.bind.DatatypeConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Configuration
@Component
public class GlobalMethod {

	public GlobalMethod() {
		super();
	}

	public Claims decodeJWT(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}

	public String substringBefore(final String str, final String separator) {
		if (str.isEmpty() || separator == null) {
			return str;
		}
		if (separator.isEmpty()) {
			return str;
		}
		final int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public String substringAfter(final String str, final String separator) {
		if (str.isEmpty() || separator == null) {
			return str;
		}
		if (separator.isEmpty()) {
			return str;
		}
		final int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(pos + 1, str.length());
	}

	public boolean checkSubstringSeparator(final String str, final String separator) {
		boolean has_separator = true;
		if (str.indexOf(separator) == -1 || str.indexOf(separator) == 0 || str.indexOf(separator) == str.length() - 1) {
			has_separator = false;
		}
		return has_separator;

	}

}
