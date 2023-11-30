package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "favourite")
public class Favourite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fav_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@Column(name = "added_date")
	private Instant addedDate;

}