package com.alkemy.disney.disney.auth.repository;

import com.alkemy.disney.disney.auth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByUsername(String username);
}
