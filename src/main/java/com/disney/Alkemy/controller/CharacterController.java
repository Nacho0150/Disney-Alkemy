package com.disney.alkemy.controller;

import com.disney.alkemy.dto.CharacterBasicDTO;
import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.exception.ParamNotFound;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    //TRAIGO AL PERSONAJE PEDIDO
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> characterDetail(@PathVariable Long id) throws ParseException, ParamNotFound{ //PARA OBTENER TODOS LAS PELICULAS
        CharacterDTO character = characterService.getCharacter(id);
        return ResponseEntity.status(HttpStatus.OK).body(character);
    } 
    
    @GetMapping("/filter")
    public ResponseEntity<List<CharacterBasicDTO>> getAllCharacters(){ //PARA OBTENER TODOS LAS PELICULAS
        List<CharacterBasicDTO> character = characterService.getAllCharacters();
        return ResponseEntity.status(HttpStatus.OK).body(character);
    } 
    
    //A ESTE @GetMapping LE TENGO QUE PONER UNA ESPECIFICACION PORQUE NO ME DEJA TENER DOS @GetMapping SIN ESPECIFICACIONES
    @GetMapping
    public ResponseEntity<List<CharacterDTO>> characterdetailFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> filmsorseries
    ) throws ParseException {
        List<CharacterDTO> characters = characterService.getCharactersFilters(name, age, filmsorseries);
        return ResponseEntity.status(HttpStatus.OK).body(characters);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO character) throws ParamNotFound, ParseException {
        CharacterDTO result = characterService.update(id, character);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}