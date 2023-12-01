package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
	private User user;

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

	@OneToMany(mappedBy = "post")
	private Set<Comment> comments = new LinkedHashSet<>();

	@OneToMany(mappedBy = "post")
	private Set<Favourite> favourites = new LinkedHashSet<>();

	@OneToMany(mappedBy = "post")
	private Set<RelPostTag> relPostTags = new LinkedHashSet<>();

}