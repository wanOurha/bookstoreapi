package com.example.bookstoreapi.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor 
//@AllArgsConstructor
//@Accessors(chain = true) setter แบบ ต่อเนื่อง และ return this
@Accessors(chain = true)
public class UserRequest {

	@NotEmpty
	@Size(min = 1)
	private String username;

	@NotEmpty
	@Length(min = 8, message = "The field must be least {min} character")
	private String password;

	@NotEmpty
	private String date_of_birth;

}
