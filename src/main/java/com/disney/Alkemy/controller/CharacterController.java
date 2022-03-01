package com.disney.alkemy.controller;

import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.services.CharacterService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("characters")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService; 
    
    @PostMapping 
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) throws ParseException{
        CharacterDTO characterSave = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSave);
    }
    
    @GetMapping 
    public ResponseEntity<List<CharacterDTO>> getAll() throws ParseException{ //PARA OBTENER TODOS LAS PELICULAS
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok().body(characters);
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}