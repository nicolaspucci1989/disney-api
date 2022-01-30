package com.alkemy.disney.disney.auth.controller;

import com.alkemy.disney.disney.auth.dto.AuthenticationRequest;
import com.alkemy.disney.disney.auth.dto.AuthenticationResponse;
import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.service.JwtUtils;
import com.alkemy.disney.disney.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  final private UserDetailsCustomService userDetailsService;
  final private AuthenticationManager authenticationManager;
  final private JwtUtils jwtUtils;

  @Autowired
  public AuthController(UserDetailsCustomService userDetailsService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.userDetailsService = userDetailsService;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("/signup")
  public ResponseEntity<AuthenticationResponse> signup(@Valid @RequestBody UserDTO user) {
    this.userDetailsService.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/signin")
  public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest) throws Exception {
    UserDetails userDetails;

    try {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          authRequest.getUsername(),
          authRequest.getPassword()
      );
      Authentication auth = authenticationManager.authenticate(authentication);
      userDetails = (UserDetails) auth.getPrincipal();
    } catch (BadCredentialsException e) {
      throw new Exception("Usuario o password incorrecot", e);
    }

    String jwt = jwtUtils.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
