package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static com.alkemy.disney.disney.TestHelper.getMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Given a character controller")
@WithMockUser
public class CharacterControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Transactional
  @Test
  @DisplayName("should return 400 when creating an invalid character")
  public void createInvalidCharacter() throws Exception {
    CharacterDTO characterDTO = CharacterDTO.builder()
        .image("/img/character.jpg")
        .age(40)
        .history("Some history")
        .name("Name")
        .build();

        mockMvc.perform(
            post("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(characterDTO))
        )
            .andExpect(status().isBadRequest());
  }

  @Transactional
  @Test
  @DisplayName("should return 201 when creating a valid character")
  public void createValidCharacter() throws Exception {
    CharacterDTO characterDTO = CharacterDTO.builder()
        .image("/img/character.jpg")
        .name("Character Name")
        .age(30)
        .weight(90f)
        .history("Some history")
        .build();

    mockMvc.perform(
        post("/characters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getMapper().writeValueAsString(characterDTO))
    )
        .andExpect(status().isCreated());
  }
}
