package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@CreationTimestamp
	private Instant createdDate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private Instant updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Instant updatedDate) {
		this.updatedDate = updatedDate;
	}

}