package com.disney.Alkemy.entities;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "filmorserie")
@Data //agrega automaticamente los @Getter, @Setter, @EqualsAndHashCode y @RequiredArgsConstructor
@SQLDelete(sql = "UPDATE car SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class FilmorSerie implements Serializable { //pelicula o serie
    @Id
    @GeneratedValue
    private String id;
    
    private String imagen;
    
    @NotNull
    private String title;
    
    @Temporal(TemporalType.DATE)
    private Date creationdate;
    
    @NotNull
    private Integer qualification; //calificacion del 1 al 5
    
    /** 
     * PARA PODER VINCULAR MUCHOS PERSONAJES A UNA PELICULA Y MUCHAS PELICULAS A UN PERSONAJE
     */
    @JoinTable(
        name = "rel_filmorserie_character",
        joinColumns = @JoinColumn(name = "FK_FILMORSERIE", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_CHARACTER", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Character> character; //personajes asociados a cada pelicula o serie

    /** 
     * PARA PODER VINCULAR MUCHOS GENEROS A UNA PELICULA Y MUCHAS PELICULAS A UN GENERO
     */
    @JoinTable(
        name = "rel_filmorserie_gender",
        joinColumns = @JoinColumn(name = "FK_FILMORSERIE", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_GENDER", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Gender> gender;
    
    private Boolean deleted;
}