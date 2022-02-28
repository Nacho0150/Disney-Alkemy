package com.disney.alkemy.entities;

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
@Table(name = "gender")
@Data
@SQLDelete(sql = "UPDATE car SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
@SequenceGenerator(
    name="GenderSeq",
    sequenceName = "GENDERS_SEQ",
    initialValue = 1, 
    allocationSize = 1
)
public class GenderEntity implements Serializable { //genero de la pelicula
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GenderSeq")
    private Long id;
    
    private String name;
    
    private String image;
    
    /** 
     * PARA PODER VINCULAR MUCHOS GENEROS A UNA PELICULA Y MUCHAS PELICULAS A UN GENERO
     */
    @ManyToMany(mappedBy = "gender") //PARA INDICAR LA RELACION BIDIRECCIONAL.
    @Column(name = "film_or_serie")
    private List<FilmorSerieEntity> filmorserie; //peliculas o series asociados
    
    private Boolean deleted;
}