package com.disney.alkemy.mapper;

import com.disney.alkemy.dto.GenderDTO;
import com.disney.alkemy.entities.GenderEntity;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {
    
    /**
     * PARA PASAR DE DTO A ENTIDAD
     * @param dto
     * @return DEVUELVE YA TODO PASADO
     */
    public GenderEntity genderDTO2Entity(GenderDTO dto){
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setName(dto.getName());
        genderEntity.setImage(dto.getImage());
        genderEntity.setFilmsorseries(dto.getFilmsorseries());
        return genderEntity;
    }
    
    /**
     * PARA PASAR DE ENTIDAD A DTO
     * @param entity
     * @return DEVUELVE YA TODO PASADO
     */
    public GenderDTO genderEntity2DTO(GenderEntity entity){
        GenderDTO dto = new GenderDTO();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setFilmsorseries(dto.getFilmsorseries());
        return dto;
    }
}