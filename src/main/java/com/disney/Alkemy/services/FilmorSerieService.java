package com.disney.alkemy.services;

import com.disney.alkemy.dto.FilmorSerieBasicDTO;
import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.exception.ParamNotFound;
import java.text.ParseException;
import java.util.List;

public interface FilmorSerieService {
    
    FilmorSerieDTO save(FilmorSerieDTO dto) throws ParseException;
    
    FilmorSerieDTO getFilmorSerie(Long id) throws ParseException, ParamNotFound;
    
    List<FilmorSerieBasicDTO> getAllFilmsorSeries() throws ParseException;
    
    List<FilmorSerieDTO> getFilmsorSeriesFilters(String title, Long gender, String creationdate, String order) throws ParseException;
    
    FilmorSerieDTO update(Long id, FilmorSerieDTO filmsorseries, boolean loadCharacters) throws ParamNotFound, ParseException;
    
    void delete(Long id) throws ParseException, ParamNotFound;
}