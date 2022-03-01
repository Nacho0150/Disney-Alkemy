package com.disney.alkemy.services;

import com.disney.alkemy.dto.CharacterDTO;
import java.text.ParseException;
import java.util.List;

public interface CharacterService {
    
    CharacterDTO save(CharacterDTO dto) throws ParseException;
    
    List<CharacterDTO> getAllCharacters() throws ParseException;
    
    void delete(Long id);
}
