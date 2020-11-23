package com.example.bookstoreapi.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.model.User;
import com.example.bookstoreapi.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loop for check user name in db
		// if not null user auth are pass
		User user = userService.findUserByUsername(username);
		if (user != null) {

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			return new org.springframework.security.core.userdetails.User(user.getUser_id() + " " + user.getUsername(),
					user.getPassword(), authorities);
		} else {
			//
			throw new UsernameNotFoundException(username + ": does not exist.");
		}
	}

}
