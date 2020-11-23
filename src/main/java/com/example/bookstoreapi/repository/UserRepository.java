package com.example.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bookstoreapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// select * from user where username = {name}
	User findByUsername(String username);

	@Query(value = "SELECT * FROM username WHERE username = :username", nativeQuery = true)
	User findUserByUsername(@Param("username") String username);
}
