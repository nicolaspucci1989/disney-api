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
    List<CharacterBasicDTO> personajes = this.characterService.getAll(name, age, movies);
    return ResponseEntity.ok(personajes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {
    CharacterDTO personaje = characterService.getDetailsById(id);
    return ResponseEntity.ok(personaje);
  }

  @PostMapping
  public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO personaje) {
    CharacterDTO personajeGuardado = characterService.save(personaje);
    return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
  }

  @PutMapping("{id}")
  public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @Valid @RequestBody CharacterDTO personaje) {
    CharacterDTO res = this.characterService.update(id, personaje);
    return ResponseEntity.ok().body(res);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.characterService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
