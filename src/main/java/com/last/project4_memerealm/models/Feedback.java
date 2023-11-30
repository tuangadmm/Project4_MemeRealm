package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
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

}