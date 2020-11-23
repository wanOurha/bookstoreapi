package com.example.bookstoreapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`Users`")
@Accessors(chain = true)
public class User {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long user_id;

	@Column(nullable = false, unique = true)
	private String username;

	@Size(min = 8)
	@Column(nullable = false)
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false)
	private Date birth_date;

}
