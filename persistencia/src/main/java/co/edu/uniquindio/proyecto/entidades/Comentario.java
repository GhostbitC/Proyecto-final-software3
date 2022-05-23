package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    int id;

    @Column(nullable = false)
    @Size(max = 500, message = "El comentario no puede superar los 200 caracteres")
    private String coment;

    @Column(nullable = false)
    private int calificacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @ManyToOne
    private Producto producto;

    //================================= RELACIÓN CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    public Comentario(String coment, int calificacion, Date fechaComentario, Producto producto, Usuario usuario) {
        this.coment = coment;
        this.calificacion = calificacion;
        this.fechaComentario = fechaComentario;
        this.producto = producto;
        this.usuario = usuario;
    }
}
