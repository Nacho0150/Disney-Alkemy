package com.disney.alkemy.dto;

import java.io.Serializable;
import lombok.Data;

@Data
/**
 * ESTE ES LO QUE VA A MOSTRAR EL LISTADO DE PELICULAS
 */
public class FilmorSerieBasicDTO implements Serializable{
    Long id;
    String image;
    String title;
    String creationdate;
}
