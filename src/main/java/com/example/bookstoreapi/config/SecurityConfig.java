package com.example.bookstoreapi.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.bookstoreapi.security.CustomUserDetailsService;
import com.example.bookstoreapi.security.JWTAuthenticationFilter;
import com.example.bookstoreapi.security.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public SecurityConfig(CustomUserDetailsService CustomUserDetailsService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.customUserDetailsService = CustomUserDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Cross Site Request Forgery การโจมตีด้วยการ
		// all request are not auth
		// not do this in real work!!!
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll() // "/users" can do
																										// anything with
																										// every one
				.antMatchers(HttpMethod.GET, "/books").permitAll() // GET("/books") can do anything with every one
				.anyRequest().authenticated() // another Request can auth before
				.and().exceptionHandling().authenticationEntryPoint((req, res, error) -> {
					res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}).and().addFilter(authenticationFilter()).sessionManagement().and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	// use this config not use default
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	// JWT Config
	@Bean
	UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		final UsernamePasswordAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
		// authenticationManager must be specified error
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

}
