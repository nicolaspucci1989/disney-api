package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.hamcrest.core.Is;
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

import static com.alkemy.disney.disney.TestHelper.getCharacterDTO;
import static com.alkemy.disney.disney.TestHelper.getMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Given a character controller")
@WithMockUser
public class CharacterControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  CharacterService characterService;

  @Transactional
  @Test
  @DisplayName("should return 400 when creating an invalid character")
  public void createInvalidCharacter() throws Exception {
    CharacterDTO characterDTO = getCharacterDTO();
    characterDTO.setName("");

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
    CharacterDTO characterDTO = getCharacterDTO();

    mockMvc.perform(
            post("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(characterDTO))
        )
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  @DisplayName("should return 200 when updating a character")
  public void updateCharacter() throws Exception {
    CharacterDTO characterDTO = getCharacterDTO();
    String originalName = "Original Name";
    String updatedName = "Updated character name";
    characterDTO.setName(originalName);
    characterService.save(characterDTO);


    mockMvc.perform(
            get("/characters/1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", Is.is(originalName)));

    characterDTO.setName(updatedName);

    mockMvc.perform(
            put("/characters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(characterDTO))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", Is.is(updatedName)));
  }
}
