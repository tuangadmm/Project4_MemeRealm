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
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@Size(max = 500)
	@NotNull
	@Column(name = "comment", nullable = false, length = 500)
	private String comment;

	@NotNull
	@Column(name = "upvote", nullable = false)
	private Integer upvote;

	@NotNull
	@Column(name = "downvote", nullable = false)
	private Integer downvote;

	@Column(name = "commented_date")
	private Instant commentedDate;

}