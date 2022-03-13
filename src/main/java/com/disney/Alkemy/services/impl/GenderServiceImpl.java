package com.disney.alkemy.services.impl;

import com.disney.alkemy.dto.GenderDTO;
import com.disney.alkemy.entities.GenderEntity;
import com.disney.alkemy.mapper.GenderMapper;
import com.disney.alkemy.repositories.GenderRepository;
import com.disney.alkemy.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService{
    
    @Autowired
    private GenderMapper genderMapper;
    
    @Autowired
    private GenderRepository genderRepository;
    
    //NO SE LES AGREGA EL OVERRIDE, PORQUE SINO, NO SE CREAN LOS BEANS
    public GenderDTO save(GenderDTO dto){
        GenderEntity entity = genderMapper.genderDTO2Entity(dto);
        GenderEntity entitySaved = genderRepository.save(entity);
        GenderDTO result = genderMapper.genderEntity2DTO(entitySaved);
        return result;
    }
    
    public void delete(Long id) {
        genderRepository.deleteById(id);
    }
}