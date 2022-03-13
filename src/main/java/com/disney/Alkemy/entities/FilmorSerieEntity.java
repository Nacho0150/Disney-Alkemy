package com.disney.alkemy.entities;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "filmorserie")
@Data //agrega automaticamente los @Getter, @Setter, @EqualsAndHashCode y @RequiredArgsConstructor
//ES PARA INDICAR QUE HAGA UN SOFTDELETE
@SQLDelete(sql = "UPDATE filmorserie SET deleted=true WHERE id = ?") //nos permite realizar un borrado lógico cuando el método delete de JPA es invocado
@Where(clause = "deleted = false") //va a permitir establecer un filtro a la hora de mostrar nuestro objeto, para diferenciar cuales fueron borrados de los que no
//
@SequenceGenerator( //PARA QUE SE INICIE SIEMPRE EL ID EN 1
    name="FilmorSerieSeq", //nombre con el que se lo referencia para que se efectue esta secuencia
    sequenceName = "FILMSORSERIES_SEQ", //el nombre que va a tener la tabla de incremento
    initialValue = 1, //numero inicial que va a tomar el ID
    allocationSize = 1 //el incremento que va a tener ese ID
)
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)  //ESTO ES POR SI SE CREAN TABLAS DUPLICADAS
/**
 * Serializable es una clase que sirve solamente para especificar que todo el estado de un objeto instanciado podrá ser escrito o enviado en la red como una trama de bytes.
 */
public class FilmorSerieEntity implements Serializable { //pelicula o serie
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FilmorSerieSeq")
    private Long id;
    
    private String image;
    private String title;
    
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationdate;
    
    private Integer qualification; //calificacion del 1 al 5
    
    /** 
     * PARA PODER VINCULAR MUCHOS PERSONAJES A UNA PELICULA Y MUCHAS PELICULAS A UN PERSONAJE
     */
    @JoinTable(
        name = "rel_filmorserie_characters",
        joinColumns = @JoinColumn(name = "FK_FILMORSERIE", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_CHARACTERS", nullable = false)
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CharacterEntity> characters = new ArrayList<>(); //personajes asociados a cada pelicula o serie
    /**
     * FetchType.EAGER, SI YO PIDO UN DATO TIPO PELICULA O SERIE, SI O SI VA A VENIR CON SU GENERO
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_id", insertable = false, updatable = false)
    private GenderEntity gender;
    
    @Column(name = "gender_id", nullable = false)
    private Long genderId;
    
    //PARA EL SOFTDELETE
    private Boolean deleted = Boolean.FALSE;
}