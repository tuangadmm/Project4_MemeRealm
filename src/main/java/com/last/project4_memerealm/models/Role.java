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
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Integer id;

	@Size(max = 20)
	@NotNull
	@Column(name = "role_name", nullable = false, length = 20)
	private String roleName;

	@OneToMany(mappedBy = "role")
	private Set<UserRole> userRoles = new LinkedHashSet<>();

}