package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	List<UserRole> findByUser_Id(Integer id);
}