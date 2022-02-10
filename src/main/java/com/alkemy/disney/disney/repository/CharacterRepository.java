package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharacterRepository extends JpaRepository<Personaje, Long>, JpaSpecificationExecutor<Personaje> {
}
