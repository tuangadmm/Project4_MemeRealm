package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Integer id;

	@Size(max = 200)
	@NotNull
	@Column(name = "caption", nullable = false, length = 200)
	private String caption;

	@Size(max = 250)
	@NotNull
	@Column(name = "media_link", nullable = false, length = 250)
	private String mediaLink;

	@NotNull
	@Column(name = "up_vote", nullable = false)
	private Integer upVote;

	@NotNull
	@Column(name = "down_vote", nullable = false)
	private Integer downVote;

	@Column(name = "uploaded_date")
	private Instant uploadedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public Integer getUpVote() {
		return upVote;
	}

	public void setUpVote(Integer upVote) {
		this.upVote = upVote;
	}

	public Integer getDownVote() {
		return downVote;
	}

	public void setDownVote(Integer downVote) {
		this.downVote = downVote;
	}

	public Instant getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Instant uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

}