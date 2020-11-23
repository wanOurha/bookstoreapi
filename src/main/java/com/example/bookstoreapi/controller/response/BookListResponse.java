package com.example.bookstoreapi.controller.response;

import java.util.List;

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
public class BookListResponse {

	private List books;

}
