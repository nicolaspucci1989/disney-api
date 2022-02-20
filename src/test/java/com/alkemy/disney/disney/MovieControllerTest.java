package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.service.GenreService;
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
import java.util.ArrayList;

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
  @Autowired
  GenreService genreService;

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

  @Transactional
  @Test
  @DisplayName("should return 201 when creating a valid movie")
  public void createValidMovie() throws Exception {

    MovieDTO movieDTO = MovieDTO.builder()
        .image("/img/image.jpg")
        .title("Movie Title")
        .creationDate(LocalDate.of(2000,1,1))
        .genreId(1L)
        .rating(3)
        .characters(new ArrayList<>())
        .build();

    mockMvc.perform(
            post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(movieDTO))
        )
        .andExpect(status().isCreated());
  }
}
