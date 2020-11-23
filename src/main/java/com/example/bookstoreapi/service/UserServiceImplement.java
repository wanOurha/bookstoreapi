package com.example.bookstoreapi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.GlobalMethod;
import com.example.bookstoreapi.controller.request.UserRequest;
import com.example.bookstoreapi.controller.response.UserInfoResponse;
import com.example.bookstoreapi.exception.DateFormatException;
import com.example.bookstoreapi.exception.UserDuplicateException;
import com.example.bookstoreapi.exception.UserNameSyntaxException;
import com.example.bookstoreapi.exception.UserNotFoundException;
import com.example.bookstoreapi.model.Orders;
import com.example.bookstoreapi.model.User;
import com.example.bookstoreapi.repository.OrdersRepository;
import com.example.bookstoreapi.repository.UserRepository;

@Service
public class UserServiceImplement implements UserService {

	private UserRepository userRepository;
	private OrdersRepository ordersRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private GlobalMethod globalMethod;

	public UserServiceImplement(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			GlobalMethod globalMethod, OrdersRepository ordersRepository) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.globalMethod = globalMethod;
		this.ordersRepository = ordersRepository;
	}

	@Override
	public User register(UserRequest userRequest) {
		Date saveBirthDate = new Date();
		try {
			String requestDate = userRequest.getDate_of_birth();
			SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
			saveBirthDate = newFormat.parse(requestDate);
		} catch (Exception e) {
			throw new DateFormatException();
		}
		String full_name = userRequest.getUsername();
		if (globalMethod.checkSubstringSeparator(full_name, ".") == false) {
			System.out.println(globalMethod.checkSubstringSeparator(full_name, "."));
			throw new UserNameSyntaxException();
		}
		User user = userRepository.findByUsername(userRequest.getUsername());
		if (user == null) {
			user = new User().setUsername(userRequest.getUsername())
					.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword())).setBirth_date(saveBirthDate);
			return userRepository.save(user);
		} else {
			throw new UserDuplicateException(userRequest.getUsername());
		}

	}

	@Override
	public User findUserByUsername(String userName) {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(userName));
		if (user.isPresent()) {
			return user.get();

		} else {
			throw new UserNotFoundException(userName);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public UserInfoResponse findUser(String jwtToken) {
		String token = globalMethod.substringAfter(jwtToken, " ");
		String name = globalMethod.decodeJWT(token).getSubject();
		User user = userRepository.findByUsername(name);
		if (user == null) {
			throw new UserNotFoundException(name);
		}
		int user_id = (int) user.getUser_id();
		ArrayList<Orders> orders = ordersRepository.findOrderByUserId(user_id);
		List<Object> order_details = new ArrayList<Object>();
		for (Orders order : orders) {
			List<Object> single_order_detail = new ArrayList<Object>();
			single_order_detail.add(order.getBook_quantity());
			single_order_detail.add(order.getBook_id());
			order_details.add(single_order_detail);
		}
		String responseDate = "";
		String requestDate = responseBirthDateFormat(user.getBirth_date() + "");
		responseDate = requestDate;
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setName(globalMethod.substringBefore(user.getUsername(), "."));
		userInfoResponse.setSurname(globalMethod.substringAfter(user.getUsername(), "."));
		userInfoResponse.setDate_of_birth(responseDate);
		userInfoResponse.setBooks(order_details);
		return userInfoResponse;
	}

	public static String responseBirthDateFormat(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		String newDate = day + "/" + month + "/" + year;
		return newDate;
	}

	@Override
	public void deleteUsersByUserId(String jwtToken) {
		String token = globalMethod.substringAfter(jwtToken, " ");
		String token_id = globalMethod.decodeJWT(token).getId();
		long user_id = Integer.parseInt(token_id);
		try {
			userRepository.deleteById(user_id);
			ordersRepository.deleteOrderByUserId(user_id);

		} catch (Exception ex) {
			throw new UserNotFoundException(user_id);
		}

	}
}
