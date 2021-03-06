package com.disney.alkemy.mapper;

import com.disney.alkemy.dto.CharacterBasicDTO;
import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.entities.CharacterEntity;
import com.disney.alkemy.entities.FilmorSerieEntity;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    
    @Autowired
    private FilmorSerieMapper filmorSerieMapper;
    
    /**
     * PARA PASAR DE DTO A ENTIDAD
     * @param dto
     * @return DEVUELVE YA TODO PASADO
     */
    public CharacterEntity characterDTO2Entity(CharacterDTO dto){
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setStory(dto.getStory());
        return characterEntity;
    }
    
    /**
     * PARA PASAR DE ENTIDAD A DTO
     * @param entity
     * @param loadFilmsorSeries
     * @return DEVUELVE YA TODO PASADO
     * @throws java.text.ParseException
     */
    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadFilmsorSeries) throws ParseException{
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName()); 
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if(loadFilmsorSeries){
            List<FilmorSerieDTO> filmorserieDTO = new ArrayList<>();
            for (FilmorSerieEntity filmorserie : entity.getFilmsorseries()) {
                filmorserieDTO.add(filmorSerieMapper.filmorserieEntity2DTO(filmorserie, false));
            }
            dto.setFilmsorseries(filmorserieDTO);
        }
        return dto;
    }
    
    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity){
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName()); 
        return dto;
    }
    
    /**
     * PARA PASAR UNA LISTA DE ENTIDADES A UNA LISTA DE DTOS BASE, REUTILIZANDO UNO DE LOS METODOS
     * @param entities
     * @return DEVUELVE YA TODO PASADO
     */
    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities){
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2BasicDTO(entity));
        }
        return dtos;
    }
    
    /**
     * PARA PASAR UNA LISTA DE ENTIDADES A UNA LISTA DE DTOS BASE, REUTILIZANDO UNO DE LOS METODOS
     * @param entities
     * @param loadFilmsorSeries
     * @return DEVUELVE YA TODO PASADO
     * @throws java.text.ParseException
     */
    public List<CharacterDTO> characterEntityList2FilterDTOList(List<CharacterEntity> entities, boolean loadFilmsorSeries) throws ParseException{
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2DTO(entity, loadFilmsorSeries));
        }
        return dtos;
    }
    
    /**
     * DONDE SE ACTUALIZAN LOS PERSONAJES
     * @param entity
     * @param characterDTO 
     */
    public void characterEntityUpdates(CharacterEntity entity, CharacterDTO characterDTO) {
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setStory(characterDTO.getStory());
    }
}