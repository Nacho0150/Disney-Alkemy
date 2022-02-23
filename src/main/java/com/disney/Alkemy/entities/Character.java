package com.disney.Alkemy.entities;

import com.sun.istack.NotNull;
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
@Table(name = "character")
@Data //agrega automaticamente los @Getter, @Setter, @EqualsAndHashCode y @RequiredArgsConstructor
@SQLDelete(sql = "UPDATE car SET deleted=true WHERE id = ?") //nos permite realizar un borrado lógico cuando el método delete de JPA es invocado
@Where(clause = "deleted = false") //va a permitir establecer un filtro a la hora de mostrar nuestro objeto
/**
 * Serializable es una clase que sirve solamente para especificar que todo el estado de un objeto instanciado podrá ser escrito o enviado en la red como una trama de bytes.
 */
public class Character implements Serializable { //personaje
    @Id
    @GeneratedValue
    private String id;
    
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
    @ManyToMany(mappedBy = "character") //PARA INDICAR LA RELACION BIDIRECCIONAL.
    private List<FilmorSerie> filmorserie; //peliculas o series asociados
    
    private Boolean deleted;
}