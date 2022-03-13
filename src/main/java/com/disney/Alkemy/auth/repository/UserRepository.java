package com.disney.alkemy.auth.repository;

import com.disney.alkemy.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    UserEntity findByUsername(String username);
}