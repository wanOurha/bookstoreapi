package com.example.bookstoreapi.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstoreapi.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM orders WHERE user_id = :user_id", nativeQuery = true)
	void deleteOrderByUserId(@Param("user_id") long user_id);

//	@Query(value = "SELECT * FROM orders WHERE user_id = :user_id", nativeQuery = true)
//	Orders findOrderByUserId(@Param("user_id") long user_id);

	@Query(value = "SELECT * FROM orders WHERE user_id = :user_id", nativeQuery = true)
	ArrayList<Orders> findOrderByUserId(@Param("user_id") long user_id);

}
