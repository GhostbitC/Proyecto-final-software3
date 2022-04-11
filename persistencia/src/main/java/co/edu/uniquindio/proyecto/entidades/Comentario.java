package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    int id;

    @Column(nullable = false,length = 200)
    @Size(max = 200, message = "El comentario no puede superar los 200 caracteres")
    private String comentario;

    @Column(nullable = false)
    private int calificacion;

    @Column(nullable = true,length = 200)
    @Size(max = 200, message = "La respuesta no puede superar los 200 caracteres")
    private String respuesta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @ManyToOne
    private Producto producto;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO USUARIO =================================//
    @ManyToOne
    private ProductoUsuario productoUsuario;

    //================================= RELACIÓN CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;


}
