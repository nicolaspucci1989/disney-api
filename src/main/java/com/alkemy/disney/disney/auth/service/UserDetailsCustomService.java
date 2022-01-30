package com.alkemy.disney.disney.auth.service;

import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.entity.AppUser;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {
  private final UserRepository userRepository;
  private final EmailService emailService;

  @Autowired
  public UserDetailsCustomService(UserRepository userRepository, EmailService emailService) {
    this.userRepository = userRepository;
    this.emailService = emailService;
  }

  public void checkIfUserExists(UserDTO dto) throws Exception {
    AppUser user = userRepository.findByUsername(dto.getUsername());
    if (user != null) {
      throw new Exception("Ya existe un usuario con ese mail");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = userRepository.findByUsername(username);

    if (appUser == null) {
      throw new UsernameNotFoundException("Username or password not found");
    }
    return new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
  }

  public boolean save(UserDTO userDTO) {
    AppUser appUser = new AppUser();
    appUser.setUsername(userDTO.getUsername());
    appUser.setPassword(userDTO.getPassword());
    appUser = this.userRepository.save(appUser);
    if (appUser != null) {
      emailService.sendWelcomeEmailTo(appUser.getUsername());
    }
    return appUser != null;
  }
}
