package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}