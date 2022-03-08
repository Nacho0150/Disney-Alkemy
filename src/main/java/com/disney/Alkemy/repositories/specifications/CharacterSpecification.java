package com.disney.alkemy.repositories.specifications;

import com.disney.alkemy.dto.CharacterFilterDTO;
import com.disney.alkemy.entities.CharacterEntity;
import com.disney.alkemy.entities.FilmorSerieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class CharacterSpecification {
    
    /**
     * ESTO ES PARA QUERYS MAS AVANZADAS, DINAMICAS COMO ESTA, SINO HACES UNA CON @QUERY Y LISTO
     * @param filterDTO
     * @return 
     */
    public Specification<CharacterEntity> getcharactersFilters(CharacterFilterDTO filterDTO){
        return (root, query, criteriaBuilder) -> {
            
            List<Predicate> predicates = new ArrayList<>();
            
            if (StringUtils.hasLength(filterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filterDTO.getName().toLowerCase() + "%")
                );
            }
            if (StringUtils.hasLength(String.valueOf(filterDTO.getAge()))){
                Integer age = filterDTO.getAge();
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), age)
                );
            }
            if (!CollectionUtils.isEmpty(filterDTO.getFilmsorseries())){
                Join<FilmorSerieEntity, CharacterEntity> toBeJoin = root.join("movies", JoinType.INNER); //join, para que pueda joinear en paises
                Expression<String> filmsorseriesId = toBeJoin.get("id"); //PARA QUE LOS TRAIGA EN BASE AL ID    //INNER PARA QUE ENTRE SI TIENEN UN ARELACION
                predicates.add(filmsorseriesId.in(filterDTO.getFilmsorseries()));
            }
            //REMOVE DUPLICATES
            query.distinct(true);   
            
            //DEVUELVE TODO LO QUE YO QUIERO MOSTRAR FILTRADO
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}