package com.disney.alkemy.services;

import com.disney.alkemy.dto.CharacterBasicDTO;
import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.exception.ParamNotFound;
import java.text.ParseException;
import java.util.List;

public interface CharacterService {
    
    CharacterDTO save(CharacterDTO dto) throws ParseException;
    
    CharacterDTO getCharacter(Long id) throws ParseException, ParamNotFound;
    
    List<CharacterBasicDTO> getAllCharacters();
    
    List<CharacterDTO> getCharactersFilters(String name, String age, List<Long> moviesId) throws ParseException;
    
    CharacterDTO update(Long id, CharacterDTO character) throws ParamNotFound, ParseException;
    
    void delete(Long id);
}