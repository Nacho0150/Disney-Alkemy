package com.disney.alkemy.controller;

import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.services.FilmorSerieService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filmorseries")
public class FilmorSerieController {
    
    @Autowired
    private FilmorSerieService filmorserieService; 
    
    @PostMapping 
    public ResponseEntity<FilmorSerieDTO> save(@RequestBody FilmorSerieDTO filmorserie) throws ParseException{
        FilmorSerieDTO filmorserieSave = filmorserieService.save(filmorserie);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmorserieSave);
    }
    
    @GetMapping 
    public ResponseEntity<List<FilmorSerieDTO>> getAll() throws ParseException{ //PARA OBTENER TODOS LAS PELICULAS
        List<FilmorSerieDTO> filmsorseries = filmorserieService.getAllFilmsorSeries();
        return ResponseEntity.ok().body(filmsorseries);
    } 
}