package com.disney.alkemy.dto;

import java.io.Serializable;
import lombok.Data;

@Data
/**
 * ESTO LO QUE VA A MOSTRAR PARA EL LISTADO DE PERSONAJES
 */
public class CharacterBasicDTO implements Serializable{
    Long id;
    String image;
    String name;
}