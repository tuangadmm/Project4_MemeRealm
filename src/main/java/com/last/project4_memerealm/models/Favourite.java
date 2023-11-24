package com.last.project4_memerealm.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "favourite")
public class Favourite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fav_id", nullable = false)
	private Integer id;

	@Column(name = "added_date")
	private Instant addedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Instant addedDate) {
		this.addedDate = addedDate;
	}

}