package com.disney.alkemy.services;

import com.disney.alkemy.dto.FilmorSerieDTO;
import java.text.ParseException;
import java.util.List;

public interface FilmorSerieService {
    
    FilmorSerieDTO save(FilmorSerieDTO dto) throws ParseException;
    
    List<FilmorSerieDTO> getAllFilmsorSeries() throws ParseException;
    
    void delete(Long id);
}