package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	List<Role> findByUserRoles_User_Id(Integer id);
}