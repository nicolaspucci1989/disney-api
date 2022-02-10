package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
