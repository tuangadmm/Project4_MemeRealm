package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public void setUpvote(Integer upvote) {
		this.upvote = upvote;
	}

	public Integer getDownvote() {
		return downvote;
	}

	public void setDownvote(Integer downvote) {
		this.downvote = downvote;
	}

	public Instant getCommentedDate() {
		return commentedDate;
	}

	public void setCommentedDate(Instant commentedDate) {
		this.commentedDate = commentedDate;
	}

}