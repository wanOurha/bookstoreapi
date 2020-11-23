package com.example.bookstoreapi.service;

import com.example.bookstoreapi.controller.request.UserRequest;
import com.example.bookstoreapi.controller.response.UserInfoResponse;
import com.example.bookstoreapi.model.User;

public interface UserService {

	User register(UserRequest userRequest);

	User findUserByUsername(String userName);

	UserInfoResponse findUser(String userName);

	void deleteUsersByUserId(String jwtToken);

}
