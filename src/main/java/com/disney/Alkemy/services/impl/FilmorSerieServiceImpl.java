package com.disney.alkemy.services.impl;

import com.disney.alkemy.dto.FilmorSerieBasicDTO;
import com.disney.alkemy.dto.FilmorSerieDTO;
import com.disney.alkemy.dto.FilmorSerieFilterDTO;
import com.disney.alkemy.entities.FilmorSerieEntity;
import com.disney.alkemy.exception.ParamNotFound;
import com.disney.alkemy.mapper.FilmorSerieMapper;
import com.disney.alkemy.repositories.FilmorSerieRepository;
import com.disney.alkemy.repositories.specifications.FilmorSerieSpecification;
import com.disney.alkemy.services.FilmorSerieService;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmorSerieServiceImpl implements FilmorSerieService{
    
    @Autowired
    private FilmorSerieMapper filmorSerieMapper;
    
    @Autowired
    private FilmorSerieRepository filmorSerieRepository;
    
    @Autowired
    private FilmorSerieSpecification filmorSerieSpecification;
    
    //NO SE LES AGREGA EL OVERRIDE, PORQUE SINO, NO SE CREAN LOS BEANS
    public FilmorSerieDTO save(FilmorSerieDTO dto) throws ParseException {
        FilmorSerieEntity entity = filmorSerieMapper.filmorserieDTO2Entity(dto, true);
        FilmorSerieEntity entitySaved = filmorSerieRepository.save(entity);
        FilmorSerieDTO result = filmorSerieMapper.filmorserieEntity2DTO(entitySaved, true);
        return result;
    }
    
    //TERMINARLO //ACA BUSCA AL PERSONAJE CON ESE ID
    public FilmorSerieDTO getFilmorSerie(Long id) throws ParseException, ParamNotFound {
        Optional<FilmorSerieEntity> filmorserie = filmorSerieRepository.findById(id);
        if (!filmorserie.isPresent()){
            throw new ParamNotFound("Id de la pelicula no valido");
        }
        FilmorSerieDTO dto = filmorSerieMapper.filmorserieEntity2DTO(filmorserie.get(), true);
        return dto;
    }

    public List<FilmorSerieBasicDTO> getAllFilmsorSeries() throws ParseException {
        List<FilmorSerieEntity> entities = filmorSerieRepository.findAll();
        List<FilmorSerieBasicDTO> result = filmorSerieMapper.filmorserieEntityList2BasicDTOList(entities);
        return result;
    }
    
    public List<FilmorSerieDTO> getFilmsorSeriesFilters(String title, String genderId, String creationdate, String order) throws ParseException{
        FilmorSerieFilterDTO filterDTO = new FilmorSerieFilterDTO(title, genderId, creationdate, order);
        List<FilmorSerieEntity> entities = filmorSerieRepository.findAll(filmorSerieSpecification.getfilmsorseriesFilters(filterDTO));
        List<FilmorSerieDTO> dtos = filmorSerieMapper.filmorserieEntityList2FilterDTOList(entities, true);
        return dtos;
    }
    
    public FilmorSerieDTO update(Long id, FilmorSerieDTO filmorserieDTO, boolean loadCharacters) throws ParamNotFound, ParseException {
        Optional<FilmorSerieEntity> entity = filmorSerieRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id de la pelicula o serie no valido");
        }
        filmorSerieMapper.filmorserieEntityUpdates(entity.get(), filmorserieDTO, loadCharacters);
        FilmorSerieEntity entitySaved = filmorSerieRepository.save(entity.get());
        FilmorSerieDTO result = filmorSerieMapper.filmorserieEntity2DTO(entitySaved, true);
        return result;
    }
    
    //BUSCA EN EL OTRO METODO PARA VER SI ESTA LA PELICULA
    public void delete(Long id) throws ParseException, ParamNotFound{
        FilmorSerieDTO filmorserie = getFilmorSerie(id);
        if (filmorserie != null) {
            filmorSerieRepository.deleteById(id);
        }
    }
}