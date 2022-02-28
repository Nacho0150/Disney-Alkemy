package com.disney.alkemy.services.impl;

import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.entities.FilmorSerieEntity;
import com.disney.alkemy.mapper.FilmorSerieMapper;
import com.disney.alkemy.repositories.FilmorSerieRepository;
import com.disney.alkemy.services.FilmorSerieService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmorSerieServiceImpl implements FilmorSerieService{
    
    @Autowired
    private FilmorSerieMapper filmorSerieMapper;
    
    @Autowired
    private FilmorSerieRepository filmorSerieRepository;
    
    //NO SE LES AGREGA EL OVERRIDE, PORQUE SINO, NO SE CREAN LOS BEANS
    public FilmorSerieDTO save(FilmorSerieDTO dto) throws ParseException {
        FilmorSerieEntity entity = filmorSerieMapper.filmorserieDTO2Entity(dto);
        FilmorSerieEntity entitySaved = filmorSerieRepository.save(entity);
        FilmorSerieDTO result = filmorSerieMapper.filmorserieEntity2DTO(entitySaved);
        return result;
    }

    public List<FilmorSerieDTO> getAllFilmsorSeries() throws ParseException {
        List<FilmorSerieEntity> entities = filmorSerieRepository.findAll();
        List<FilmorSerieDTO> result = filmorSerieMapper.filmorserieEntityList2DTOList(entities);
        return result;
    }
}