package com.disney.alkemy.controller;

import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.services.FilmorSerieService;
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
@RequestMapping("movies")
public class FilmorSerieController {
    
    @Autowired
    private FilmorSerieService filmorserieService; 
    
    @PostMapping 
    public ResponseEntity<FilmorSerieDTO> save(@RequestBody FilmorSerieDTO filmorserie) throws ParseException{
        FilmorSerieDTO filmorserieSave = filmorserieService.save(filmorserie);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmorserieSave);
    }
    
    @GetMapping 
    public ResponseEntity<List<FilmorSerieDTO>> filmorserieDetail() throws ParseException{ //PARA OBTENER TODO SOBRE
        List<FilmorSerieDTO> filmsorseries = filmorserieService.getAllFilmsorSeries();     //PELICULAS O SERIES.
        return ResponseEntity.ok().body(filmsorseries);
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        filmorserieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}