package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.MovieDTO;
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

import java.time.LocalDate;

import static com.alkemy.disney.disney.TestHelper.getMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Given a movie controller")
@WithMockUser
public class MovieControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Transactional
  @Test
  @DisplayName("should return 400 when creating an invalid movie")
  public void createInvalidMovie() throws Exception {
    MovieDTO movieDTO = MovieDTO.builder()
        .image("/img/image.jpg")
        .creationDate(LocalDate.of(2000,1,1))
        .rating(3)
        .build();

    mockMvc.perform(
        post("/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getMapper().writeValueAsString(movieDTO))
    )
        .andExpect(status().isBadRequest());
  }
}
