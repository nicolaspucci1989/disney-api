package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genre, Long> {
}
