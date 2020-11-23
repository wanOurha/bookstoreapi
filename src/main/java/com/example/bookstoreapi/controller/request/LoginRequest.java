package com.example.bookstoreapi.controller.request;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class LoginRequest {

	private String username;

	@Size(min = 8)
	private String password;

}
