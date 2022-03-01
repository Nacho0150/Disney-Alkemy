package com.disney.alkemy.dto;

import com.disney.alkemy.entities.FilmorSerieEntity;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class GenderDTO implements Serializable{
    String id;
    String name;
    String image;
    List<FilmorSerieEntity> filmsorseries;
}