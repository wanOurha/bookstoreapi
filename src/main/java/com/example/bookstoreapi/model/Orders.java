package com.example.bookstoreapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`Orders`")
@Accessors(chain = true)
public class Orders {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long order_id;

	@Column(nullable = false)
	private long user_id;

	@Column(nullable = false)
	private long book_id;

	@Column(nullable = false)
	private long book_quantity;

	@Column(nullable = false)
	private double total_price;

}
