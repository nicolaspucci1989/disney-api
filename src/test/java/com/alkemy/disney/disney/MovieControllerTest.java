package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.GenreService;
import com.alkemy.disney.disney.service.MovieService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
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

import static com.alkemy.disney.disney.TestHelper.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
  MovieService movieService;
  @Autowired
  GenreService genreService;

  @Transactional
  @Test
  @DisplayName("should return 400 when creating an invalid movie")
  public void createInvalidMovie() throws Exception {
    MovieDTO movieDTO = getMovieDTO();
    movieDTO.setTitle("");

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
    GenreDTO genreDTO = new GenreDTO();
    genreDTO.setImage("/img/fiction.jpg");
    genreDTO.setName("Fiction");
    GenreDTO saveGenreDTO = genreService.save(genreDTO);

    MovieDTO movieDTO = getMovieDTO();
    movieDTO.setGenreId(saveGenreDTO.getId());

    mockMvc.perform(
            post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(movieDTO))
        )
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  @DisplayName("should return 200 when updating a movie")
  public void updateMovie() throws Exception {
    GenreDTO genreDTO = new GenreDTO();
    genreDTO.setImage("/img/fiction.jpg");
    genreDTO.setName("Fiction");
    genreService.save(genreDTO);
    MovieDTO movieDTO = getMovieDTO();
    String originalTitle = "Original movie title";
    movieDTO.setTitle(originalTitle);
    movieDTO.setGenreId(1L);
    movieService.save(movieDTO);

    mockMvc.perform(
            get("/movies/1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title", Is.is(originalTitle)));

    String title = "Updated movie title";
    movieDTO.setTitle(title);

    mockMvc.perform(
            put("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(movieDTO))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title", Is.is(title)));
  }
}
