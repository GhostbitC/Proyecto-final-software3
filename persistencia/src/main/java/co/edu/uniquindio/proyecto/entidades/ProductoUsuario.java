package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto_usuario",nullable = false)
    @EqualsAndHashCode.Include
    private int codigoProductoUsuario;

    @Column(name = "nombre",length = 100,nullable = false)
    private String nombre;

    @Column(name = "descripcion",length = 500)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "cantidad", nullable = false)
    private  int cantidad;

    @Column(name = "estado", nullable =false)
    private Boolean estado;

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACION CON LA ENTIDAD CATEGORIAPRODUCTO =================================//
    @ManyToOne
    private CategoriaProducto categoria;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD RESENIA =================================//
    @OneToMany(mappedBy = "productoUsuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    //================================= RELACION CON LA ENTIDAD IMAGEN =================================//
    @OneToMany(mappedBy = "productoUsuario",fetch=FetchType.EAGER)
    @ToString.Exclude
    private List<Imagen> imagenes;

    public ProductoUsuario(String nombre, String descripcion, double precio, Administrador administrador, CategoriaProducto categoria, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.administrador = administrador;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public String getImagenPrincipal(){

        if(imagenes!=null && !imagenes.isEmpty()){

            return imagenes.get(0).getUrl();
        }

        return "default.png";
    }

}
