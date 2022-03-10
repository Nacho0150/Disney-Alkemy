package com.disney.alkemy.services.impl;

import com.disney.alkemy.dto.CharacterBasicDTO;
import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.dto.CharacterFilterDTO;
import com.disney.alkemy.entities.CharacterEntity;
import com.disney.alkemy.exception.ParamNotFound;
import com.disney.alkemy.mapper.CharacterMapper;
import com.disney.alkemy.repositories.CharacterRepository;
import com.disney.alkemy.repositories.specifications.CharacterSpecification;
import com.disney.alkemy.services.CharacterService;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService{
    
    @Autowired
    private CharacterMapper characterMapper;
    
    @Autowired
    private CharacterRepository characterRepository;
    
    @Autowired
    private CharacterSpecification characterSpecification;
    
    //NO SE LES AGREGA EL OVERRIDE, PORQUE SINO, NO SE CREAN LOS BEANS
    public CharacterDTO save(CharacterDTO dto) throws ParseException{
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    //TERMINARLO //ACA BUSCA AL PERSONAJE CON ESE ID
    public CharacterDTO getCharacter(Long id) throws ParseException, ParamNotFound {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if (!character.isPresent()){
            throw new ParamNotFound("Id del personaje no valido");
        }
        CharacterDTO dto = characterMapper.characterEntity2DTO(character.get(), true);
        return dto;
    }
    
//    //ACA TERMINA DE HACER EL TRABAJO DE BUSQUEDA DE LOSA TRIBUTOS
//    public CharacterDTO getCharacter2(Long id) throws ParseException, ParamNotFound{
//        CharacterEntity character = getCharacter(id);
//        CharacterDTO dto = characterMapper.characterEntity2DTO(character, true);
//        return dto;
//    }
    
    public List<CharacterBasicDTO> getAllCharacters(){
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2BasicDTOList(entities);
        return result;
    }
    
    public List<CharacterDTO> getCharactersFilters(String name, Integer age, List<Long> filmsorseries) throws ParseException{
        CharacterFilterDTO filterDTO = new CharacterFilterDTO(name, age, filmsorseries);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getcharactersFilters(filterDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntityList2FilterDTOList(entities, true);
        return dtos;
    }
    
    public CharacterDTO update(Long id, CharacterDTO characterDTO) throws ParamNotFound, ParseException {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id del personaje no valido");
        }
        characterMapper.characterEntityUpdates(entity.get(), characterDTO);
        CharacterEntity entitySaved = characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }
}