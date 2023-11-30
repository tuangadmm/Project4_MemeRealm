package com.last.project4_memerealm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id", nullable = false)
	private Integer id;

	@Size(max = 100)
	@NotNull
	@Column(name = "tag_name", nullable = false, length = 100)
	private String tagName;

	@OneToMany(mappedBy = "tag")
	private Set<RelPostTag> relPostTags = new LinkedHashSet<>();

}