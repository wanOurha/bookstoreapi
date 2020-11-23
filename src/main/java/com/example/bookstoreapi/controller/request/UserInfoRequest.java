package com.example.bookstoreapi.controller.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

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
public class UserInfoRequest {

	@NotEmpty
	private String name;

	@NotEmpty
	private String surname;

	@NotEmpty
	private String date_of_birth;

	private List books;

}
