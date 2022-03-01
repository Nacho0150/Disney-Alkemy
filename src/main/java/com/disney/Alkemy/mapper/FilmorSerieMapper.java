package com.disney.alkemy.mapper;

import com.disney.alkemy.dto.CharacterDTO;
import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.entities.CharacterEntity;
import com.disney.alkemy.entities.FilmorSerieEntity;
import com.disney.alkemy.util.Util;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //esto va a permitir inyectarlo
public class FilmorSerieMapper {
    
    @Autowired
    private CharacterMapper characterMapper;
    /**
     * PARA PASAR DE DTO A ENTIDAD
     * @param dto
     * @param loadCharacters PARA SABER SI QUIERO O NO CARGAR ESE ATRIBUTO, PARA EVITAR BUCLES DE INFORMACION
     * @return DEVUELVE YA TODO PASADO
     * @throws ParseException 
     */
    public FilmorSerieEntity filmorserieDTO2Entity(FilmorSerieDTO dto, boolean loadCharacters) throws ParseException{
        FilmorSerieEntity filmorSerieEntity = new FilmorSerieEntity();
        filmorSerieEntity.setImage(dto.getImage());
        filmorSerieEntity.setTitle(dto.getTitle());
        filmorSerieEntity.setCreationdate(Util.stringToDate(dto.getCreationdate()));
        filmorSerieEntity.setQualification(dto.getQualification());
        filmorSerieEntity.setGenderId(dto.getGenderId());
        if (loadCharacters){
            List<CharacterEntity> characterEntity = new ArrayList<>();
            for (CharacterDTO personajeDTO : dto.getCharacters()) {
                characterEntity.add(characterMapper.characterDTO2Entity(personajeDTO));
            }
            filmorSerieEntity.setCharacters(characterEntity);
        }
        return filmorSerieEntity;
    }
    
    /**
     * PARA PASAR DE ENTIDAD A DTO
     * @param entity
     * @param loadCharacters
     * @return DEVUELVE YA TODO PASADO
     * @throws ParseException 
     */
    public FilmorSerieDTO filmorserieEntity2DTO(FilmorSerieEntity entity, boolean loadCharacters) throws ParseException{
        FilmorSerieDTO dto = new FilmorSerieDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationdate(Util.dateToString(entity.getCreationdate()));
        dto.setQualification(dto.getQualification());
        dto.setGenderId(dto.getGenderId());
        if(loadCharacters){
            List<CharacterDTO> characterDTO = new ArrayList<>();
            for (CharacterEntity character : entity.getCharacters()) {
                characterDTO.add(characterMapper.characterEntity2DTO(character, false));
            }
            dto.setCharacters(characterDTO);
        }
        return dto;
    }
    
    /**
     * PARA PASAR UNA LISTA DE ENTIDADES A UNA LISTA DE DTOS, REUTILIZANDO UNO DE LOS METODOS
     * @param entities
     * @param loadCharacters
     * @return DEVUELVE YA TODO PASADO
     * @throws ParseException 
     */
    public List<FilmorSerieDTO> filmorserieEntityList2DTOList(List<FilmorSerieEntity> entities, boolean loadCharacters) throws ParseException{
        List<FilmorSerieDTO> dtos = new ArrayList<>();
        for (FilmorSerieEntity entity : entities) {
            dtos.add(filmorserieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }
}