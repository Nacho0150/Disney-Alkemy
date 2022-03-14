package com.disney.alkemy.controller;

import com.disney.alkemy.dto.FilmorSerieBasicDTO;
import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.exception.ParamNotFound;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class FilmorSerieController {

    @Autowired
    private FilmorSerieService filmorserieService;

    @PostMapping
    public ResponseEntity<FilmorSerieDTO> save(@RequestBody FilmorSerieDTO filmorserie) throws ParseException {
        FilmorSerieDTO filmorserieSave = filmorserieService.save(filmorserie);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmorserieSave);
    }

    //TRAIGO AL PERSONAJE PEDIDO
    @GetMapping("/{id}")
    public ResponseEntity<FilmorSerieDTO> filmorserieDetail(@PathVariable Long id) throws ParseException, ParamNotFound { //PARA OBTENER TODO SOBRE
        FilmorSerieDTO filmsorseries = filmorserieService.getFilmorSerie(id);     //PELICULAS O SERIES.
        return ResponseEntity.ok().body(filmsorseries);
    }

    @GetMapping
    public ResponseEntity<List<FilmorSerieBasicDTO>> getAllFilmorSerie() throws ParseException { //PARA OBTENER TODO SOBRE
        List<FilmorSerieBasicDTO> filmsorseries = filmorserieService.getAllFilmsorSeries();     //PELICULAS O SERIES.
        return ResponseEntity.ok().body(filmsorseries);
    }

    //A ESTE @GetMapping LE TENGO QUE PONER UNA ESPECIFICACION PORQUE NO ME DEJA TENER DOS @GetMapping SIN ESPECIFICACIONES
    @GetMapping("/filter")
    public ResponseEntity<List<FilmorSerieDTO>> filmorseriedDetailFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genderId,
            @RequestParam(required = false) String creationdate,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) throws ParseException {
        List<FilmorSerieDTO> filmsorseries = filmorserieService.getFilmsorSeriesFilters(title, genderId, creationdate, order);
        return ResponseEntity.status(HttpStatus.OK).body(filmsorseries);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FilmorSerieDTO> update(@PathVariable Long id, @RequestBody FilmorSerieDTO filmsorseries) throws ParamNotFound, ParseException {
        FilmorSerieDTO result = filmorserieService.update(id, filmsorseries, false);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ParseException, ParamNotFound {
        filmorserieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}