package com.example.bookstoreapi.controller.api;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.controller.request.LoginRequest;
import com.example.bookstoreapi.controller.request.UserRequest;
import com.example.bookstoreapi.controller.response.UserInfoResponse;
import com.example.bookstoreapi.exception.ValidationException;
import com.example.bookstoreapi.model.User;
import com.example.bookstoreapi.service.UserServiceImplement;

@RestController
public class UserController {

	private final UserServiceImplement userServiceImplement;

	UserController(UserServiceImplement userServiceImplement) {
		this.userServiceImplement = userServiceImplement;
	}

	// @RequestBody get JSON
	@PostMapping(path = "/users")
	public void register(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		userServiceImplement.register(userRequest);
	}

	@GetMapping(path = "/users")
	public UserInfoResponse getUser(@RequestHeader("Authorization") String token) {
		return userServiceImplement.findUser(token);
	}

	@DeleteMapping(path = "/users")
	public void deleteUsers(@RequestHeader("Authorization") String jwtToken) {
		userServiceImplement.deleteUsersByUserId(jwtToken);
	}

	@PostMapping(path = "/login")
	public User login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return userServiceImplement.findUserByUsername(loginRequest.getUsername());
	}

}
