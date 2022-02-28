package com.disney.alkemy.repositories;

import com.disney.alkemy.entities.FilmorSerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmorSerieRepository extends JpaRepository<FilmorSerieEntity, Long>{
    
}