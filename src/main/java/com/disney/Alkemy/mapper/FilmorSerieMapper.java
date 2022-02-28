package com.disney.alkemy.mapper;

import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.entities.FilmorSerieEntity;
import com.disney.alkemy.util.Util;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component //esto va a permitir inyectarlo
public class FilmorSerieMapper {
    
    /**
     * PARA PASAR DE DTO A ENTIDAD
     * @param dto
     * @return DEVUELVE YA TODO PASADO
     * @throws ParseException 
     */
    public FilmorSerieEntity filmorserieDTO2Entity(FilmorSerieDTO dto) throws ParseException{
        FilmorSerieEntity filmorSerieEntity = new FilmorSerieEntity();
        filmorSerieEntity.setImage(dto.getImage());
        filmorSerieEntity.setTitle(dto.getTitle());
        filmorSerieEntity.setCreationdate(Util.stringToDate(dto.getCreationdate()));
        filmorSerieEntity.setQualification(dto.getQualification());
        filmorSerieEntity.setCharacters(dto.getCharacters());
        filmorSerieEntity.setGenderId(dto.getGenderId());
        return filmorSerieEntity;
    }
    
    /**
     * PARA PASAR DE ENTIDAD A DTO
     * @param entity
     * @return DEVUELVE YA TODO PASADO
     * @throws ParseException 
     */
    public FilmorSerieDTO filmorserieEntity2DTO(FilmorSerieEntity entity) throws ParseException{
        FilmorSerieDTO dto = new FilmorSerieDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationdate(Util.dateToString(entity.getCreationdate()));
        dto.setQualification(dto.getQualification() );
        dto.setCharacters(dto.getCharacters());
        dto.setGenderId(dto.getGenderId());
        return dto;
    }
    
    /**
     * PARA PASAR UNA LISTA DE ENTIDADES A UNA LISTA DE DTOS, REUTILIZANDO UNO DE LOS METODOS
     * @param entities
     * @return DEVUELVE YA TODO PASADO
     * @throws ParseException 
     */
    public List<FilmorSerieDTO> filmorserieEntityList2DTOList(List<FilmorSerieEntity> entities) throws ParseException{
        List<FilmorSerieDTO> dtos = new ArrayList<>();
        for (FilmorSerieEntity entity : entities) {
            dtos.add(this.filmorserieEntity2DTO(entity));
        }
        return dtos;
    }
}