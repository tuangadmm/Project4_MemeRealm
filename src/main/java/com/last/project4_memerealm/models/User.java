package com.last.project4_memerealm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_id", nullable = false)
	private Integer id;

	@Size(max = 30)
	@NotNull
	@Column(name = "username", nullable = false, length = 30)
	private String username;

	@Size(max = 250)
	@NotNull
	@Column(name = "password", nullable = false, length = 250)
	private String password;

	@Size(max = 100)
	@NotNull
	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Size(max = 250)
	@Column(name = "avatar", length = 250)
	private String avatar;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "updated_date")
	private Instant updatedDate;

}