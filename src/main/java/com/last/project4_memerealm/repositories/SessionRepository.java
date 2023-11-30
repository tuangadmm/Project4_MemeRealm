package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {
	boolean existsBySessionToken(String sessionToken);

	Optional<Session> findBySessionToken(String sessionToken);

}