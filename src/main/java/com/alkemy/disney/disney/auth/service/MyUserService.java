package com.alkemy.disney.disney.auth.service;

import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.entity.AppUser;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EmailService emailService;

  public void checkIfUserExists(UserDTO dto) throws Exception {
    AppUser user = userRepository.findByUsername(dto.getUsername());
    if (user != null) {
      throw new Exception("Ya existe un usuario con ese mail");
    }
  }

  public boolean save(UserDTO userDTO) {
    AppUser appUser = new AppUser();
    appUser.setUsername(userDTO.getUsername());
    appUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    appUser = this.userRepository.save(appUser);
    if (appUser != null) {
      emailService.sendWelcomeEmailTo(appUser.getUsername());
    }
    return appUser != null;
  }
}
