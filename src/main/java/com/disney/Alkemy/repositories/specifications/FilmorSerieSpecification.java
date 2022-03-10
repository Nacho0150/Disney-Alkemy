package com.disney.alkemy.repositories.specifications;

import com.disney.alkemy.dto.FilmorSerieFilterDTO;
import com.disney.alkemy.entities.FilmorSerieEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FilmorSerieSpecification {

    /**
     * ESTO ES PARA QUERYS MAS AVANZADAS, DINAMICAS COMO ESTA, SINO HACES UNA CON @QUERY Y LISTO
     *
     * @param filterDTO
     * @return
     */
    public Specification<FilmorSerieEntity> getfilmsorseriesFilters(FilmorSerieFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getTitle().toLowerCase() + "%")
                );
            }
            if (StringUtils.hasLength(String.valueOf(filterDTO.getGenderId()))) {
                Long genderId = filterDTO.getGenderId();
                predicates.add(
                        criteriaBuilder.equal(root.get("gender"), genderId)
                );
            }
            if (StringUtils.hasLength(filterDTO.getCreationdate())) {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date date = null;
                try {
                    date = format.parse(filterDTO.getCreationdate());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                predicates.add(
                        criteriaBuilder.equal((root.<Date>get("creationdate")), date)
                );
            }
            //REMOVE DUPLICATES
            query.distinct(true);
            
            //PARA QUE LO ORDENE POR ASCENDENTE O DESCENDENTE
            String orderByField = "creationdate";
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            //DEVUELVE TODO LO QUE YO QUIERO MOSTRAR FILTRADO
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}