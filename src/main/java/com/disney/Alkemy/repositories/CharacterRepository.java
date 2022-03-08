package com.disney.alkemy.repositories;

import com.disney.alkemy.entities.CharacterEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity>{
    
    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}