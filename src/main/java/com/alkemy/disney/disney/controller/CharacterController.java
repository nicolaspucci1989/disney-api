package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

  CharacterService characterService;

  @Autowired
  public CharacterController(CharacterService characterService) {
    this.characterService = characterService;
  }

  @GetMapping
  public ResponseEntity<List<CharacterBasicDTO>> getAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) List<Long> movies
  ) {
    List<CharacterBasicDTO> characters = this.characterService.getAll(name, age, movies);
    return ResponseEntity.ok(characters);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {
    CharacterDTO character = characterService.getDetailsById(id);
    return ResponseEntity.ok(character);
  }

  @PostMapping
  public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO character) {
    CharacterDTO saveCharacter = characterService.save(character);
    return ResponseEntity.status(HttpStatus.CREATED).body(saveCharacter);
  }

  @PutMapping("{id}")
  public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @Valid @RequestBody CharacterDTO character) {
    CharacterDTO res = this.characterService.update(id, character);
    return ResponseEntity.ok().body(res);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.characterService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
