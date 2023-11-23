package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}