package com.disney.alkemy.services.impl;

import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.entities.CharacterEntity;
import com.disney.alkemy.mapper.CharacterMapper;
import com.disney.alkemy.repositories.CharacterRepository;
import com.disney.alkemy.services.CharacterService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService{
    
    @Autowired
    private CharacterMapper characterMapper;
    
    @Autowired
    private CharacterRepository characterRepository;
    
    //NO SE LES AGREGA EL OVERRIDE, PORQUE SINO, NO SE CREAN LOS BEANS
    public CharacterDTO save(CharacterDTO dto) throws ParseException{
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    public List<CharacterDTO> getAllCharacters() throws ParseException{
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities, false);
        return result;
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }
}