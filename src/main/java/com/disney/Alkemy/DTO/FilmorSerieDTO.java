package com.disney.alkemy.dto;

import com.disney.alkemy.entities.CharacterEntity;
import java.util.List;
import lombok.Data;

@Data
public class FilmorSerieDTO {
    Long id;
    String image;
    String title;
    String creationdate;
    Integer qualification;
    List<CharacterEntity> characters;
    String genderId;
}