package com.example.bookstoreapi.security;

public interface SecurityConstants {

	String SECRET_KEY = "seCret1"; // ket to encode
	String TOKEN_PREFIX = "Bearer "; // Auth type
	String HEADER_AUTHORIZATION = "Authorization"; // Auth Header
	long EXPIRATION_TIME = (5 * 60 * 1000); // 1000 * 60 = 1 min
	String CLAIMS_ROLE = "role";

}
