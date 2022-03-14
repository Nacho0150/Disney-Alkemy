package com.disney.alkemy.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


/**
 * ESTO LO QUE VA A MOSTRAR PARA EL LISTADO DE PERSONAJES
 */
@Getter
@Setter
public class CharacterBasicDTO implements Serializable{
    Long id;
    String image;
    String name;
}