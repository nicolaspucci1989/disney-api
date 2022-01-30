package com.alkemy.disney.disney.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDTO {
  @Email(message = "Username debe ser mail")
  private String username;

  @Size(min = 9)
  @NotNull
  private String password;
}
