package com.disney.alkemy.entities;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "characters")
@Data
@SQLDelete(sql = "UPDATE car SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
@SequenceGenerator(
    name="CharactersSeq",
    sequenceName = "CHARACTERS_SEQ",
    initialValue = 1, 
    allocationSize = 1
)
public class CharacterEntity implements Serializable { //personaje
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CharactersSeq")
    private Long id;
    
    private String image;
    
    @NotNull
    private String name;
    
    @NotNull
    private Integer age;
    
    @NotNull
    private Double weight;
    
    @NotNull
    private String story;
    
    /** 
     * PARA PODER VINCULAR MUCHOS PERSONAJES A UNA PELICULA Y MUCHAS PELICULAS A UN PERSONAJE
     */
    @ManyToMany(mappedBy = "characters") //PARA INDICAR LA RELACION BIDIRECCIONAL.
    @Column(name = "film_or_serie")
    private List<FilmorSerieEntity> filmorserie; //peliculas o series asociados
    
    private Boolean deleted;
}