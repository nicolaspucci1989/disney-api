package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharacterRepository extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {
}
