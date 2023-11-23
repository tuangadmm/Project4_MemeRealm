package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "sessions")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id", nullable = false)
	private Integer id;

	@Size(max = 250)
	@NotNull
	@Column(name = "session_token", nullable = false, length = 250)
	private String sessionToken;

	@Column(name = "expire_time")
	private Instant expireTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Instant getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Instant expireTime) {
		this.expireTime = expireTime;
	}

}