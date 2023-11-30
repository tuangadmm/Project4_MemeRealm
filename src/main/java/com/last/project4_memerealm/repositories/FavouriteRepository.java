package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
}