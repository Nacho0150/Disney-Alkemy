package com.disney.alkemy.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmorSerieDTO {
    Long id;
    String image;
    String title;
    String creationdate;
    Integer qualification;
    List<CharacterDTO> characters;
    Long genderId;
}