package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "feedbacks")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id", nullable = false)
	private Integer id;

	@Size(max = 1000)
	@NotNull
	@Column(name = "message", nullable = false, length = 1000)
	private String message;

	@Column(name = "sent_date")
	private Instant sentDate;

	@Column(name = "answered_date")
	private Instant answeredDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getSentDate() {
		return sentDate;
	}

	public void setSentDate(Instant sentDate) {
		this.sentDate = sentDate;
	}

	public Instant getAnsweredDate() {
		return answeredDate;
	}

	public void setAnsweredDate(Instant answeredDate) {
		this.answeredDate = answeredDate;
	}

}