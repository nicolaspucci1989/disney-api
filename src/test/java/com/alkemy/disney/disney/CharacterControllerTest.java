package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

  private static ObjectMapper getMapper() {
    var mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }
}
