package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Favorito {


    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    //================================= RELACION CON LA ENTIDAD PRODUCTO =================================//
    @ManyToOne
    @ToString.Exclude
    private Producto producto;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;


}
