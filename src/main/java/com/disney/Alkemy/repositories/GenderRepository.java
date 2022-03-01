package com.disney.alkemy.repositories;

import com.disney.alkemy.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long>{
    
}