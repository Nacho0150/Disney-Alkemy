package com.disney.alkemy.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO implements Serializable {
    Long id;
    String image;
    String name;
    Integer age;
    Double weight;
    String story;
    List<FilmorSerieDTO> filmsorseries;
}