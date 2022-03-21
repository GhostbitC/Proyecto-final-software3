package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Imagen implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "url",length = 100,nullable = false)
    @NotBlank
    private String url;

    //================================= RELACION CON LA ENTIDAD MASCOTA =================================//
    @ManyToOne
    private Producto producto;

    //================================= RELACION CON LA ENTIDAD MASCOTA =================================//
    @ManyToOne
    private ProductoUsuario productoUsuario;

    //================================= CONSTRUCTOR  =================================//
    public Imagen(String url) {
        this.url = url;
    }

}
