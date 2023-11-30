package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}