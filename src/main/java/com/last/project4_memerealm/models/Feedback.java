package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assistor_id", referencedColumnName = "user_id")
	private User assistor;

	@Size(max = 1000)
	@NotNull
	@Column(name = "message", nullable = false, length = 1000)
	private String message;

	@Column(name = "sent_date")
	private Instant sentDate;

	@Column(name = "answered_date")
	private Instant answeredDate;

}