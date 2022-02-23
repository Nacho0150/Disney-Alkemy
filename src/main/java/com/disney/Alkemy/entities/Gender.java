package com.disney.Alkemy.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "gender")
@Data //agrega automaticamente los @Getter, @Setter, @EqualsAndHashCode y @RequiredArgsConstructor
@SQLDelete(sql = "UPDATE car SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class Gender implements Serializable { //genero de la pelicula
    @Id
    @GeneratedValue
    private String id;
    
    private String name;
    
    private String image;
    
    /** 
     * PARA PODER VINCULAR MUCHOS GENEROS A UNA PELICULA Y MUCHAS PELICULAS A UN GENERO
     */
    @ManyToMany(mappedBy = "gender") //PARA INDICAR LA RELACION BIDIRECCIONAL.
    private List<FilmorSerie> filmorserie; //peliculas o series asociados
    
    private Boolean deleted;
}