package com.disney.alkemy.controller;

import com.disney.alkemy.dto.GenderDTO;
import com.disney.alkemy.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genders")
public class GenderController {
    
    @Autowired
    private GenderService genderService; 
    
    @PostMapping 
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO character){
        GenderDTO characterSave = genderService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSave);
    }
}