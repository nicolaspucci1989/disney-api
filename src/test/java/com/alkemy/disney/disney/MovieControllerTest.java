package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
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

import static com.alkemy.disney.disney.TestHelper.getMapper;
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
  CharacterService characterService;
  @Autowired
  GenreService genreService;

  @BeforeEach
  public void setup() {
    MovieDTO movieDTOOne = getMovieDTO();
    MovieDTO movieDTOTwo = getMovieDTO();

    CharacterDTO characterDTOOne = getCharacterDTO();
    CharacterDTO characterDTOTwo = getCharacterDTO();

    movieDTOOne.setTitle("Movie One");
    movieDTOTwo.setTitle("Movie One");

    characterDTOOne.setName("Character One");
    characterDTOTwo.setName("Character Two");

    GenreDTO genreDTO = new GenreDTO();
    genreDTO.setName("Genre");
    genreDTO.setImage("/img/genre.jpg");
    genreService.save(genreDTO);

    movieService.save(movieDTOOne);
    movieService.save(movieDTOTwo);

    characterService.save(characterDTOOne);
    characterService.save(characterDTOTwo);
  }

  @Test
  @DisplayName("should return 400 when creating an invalid movie")
  public void createInvalidMovie() throws Exception {
    MovieDTO movieDTO = MovieDTO.builder()
        .image("/img/image.jpg")
        .creationDate(LocalDate.of(2000, 1, 1))
        .rating(3)
        .characters(new ArrayList<>())
        .genreId(1L)
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
        .creationDate(LocalDate.of(2000, 1, 1))
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

  @Transactional
  @Test
  @DisplayName("should return 200 when updating a movie")
  public void updateMovie() throws Exception {
    MovieDTO movieDTO = getMovieDTO();
    String title = "Updated movie title";
    movieDTO.setTitle(title);

    mockMvc.perform(
            get("/movies/1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title", Is.is("Movie One")));

    mockMvc.perform(
            put("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(movieDTO))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title", Is.is(title)));
  }

  private MovieDTO getMovieDTO() {
    return MovieDTO.builder()
        .characters(new ArrayList<>())
        .title("Title")
        .genreId(1L)
        .creationDate(LocalDate.of(2000, 1, 1))
        .image("/img/movie.jpg")
        .rating(5)
        .build();
  }

  private CharacterDTO getCharacterDTO() {
    return CharacterDTO.builder()
        .image("/img/character.jpg")
        .name("Character")
        .age(30)
        .weight(90f)
        .history("History")
        .movies(new ArrayList<>())
        .build();
  }
}
