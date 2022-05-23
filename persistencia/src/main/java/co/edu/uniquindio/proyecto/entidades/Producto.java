package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int unidades;

    @Column(nullable =false)
    private Boolean estado;

    //================================= RELACIÓN CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACIÓN CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= RELACIÓN CON LA ENTIDAD CATEGORÍA =================================//
    @ManyToOne
    private Categoria categoria;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @ManyToOne
    private Compra compra;

    //================================= RELACIÓN CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    //================================= RELACIÓN CON LA ENTIDAD IMAGEN =================================//
    @OneToMany(mappedBy = "producto",fetch=FetchType.LAZY)
    @ToString.Exclude
    private List<Imagen> imagenes;

    //================================= RELACIÓN CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Favorito> favoritos;

    //================================= RELACIÓN CON LA ENTIDAD ESPECIFICACIÓN =================================//
    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Especificacion> especificaciones;

    //================================= RELACIÓN CON LA ENTIDAD DETALLE COMPRA =================================//
    @OneToMany (mappedBy = "producto", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> listaDetalleCompra;


    public Producto(String nombre, String descripcion, double precio, Administrador administrador, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setEstado(true);
        this.precio = precio;
        this.administrador = administrador;
        this.categoria = categoria;
        this.imagenes = new ArrayList<>();
        this.comentarios=new ArrayList<>();
        this.especificaciones = new ArrayList<>();
    }

    public Producto(String nombre, String descripcion, double precio, int unidades, Boolean estado, Administrador administrador, Usuario usuario, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades = unidades;
        this.estado = estado;
        this.administrador = administrador;
        this.usuario = usuario;
        this.categoria = categoria;
        this.imagenes = new ArrayList<>();
        this.comentarios=new ArrayList<>();
        this.especificaciones = new ArrayList<>();
    }

    public Producto(String nombre, String descripcion, double precio, int unidades, Usuario usuario, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades = unidades;
        this.usuario = usuario;
        this.categoria = categoria;
        this.imagenes = new ArrayList<>();
        this.comentarios=new ArrayList<>();
        this.especificaciones = new ArrayList<>();
    }

    public String getImagenPrincipal(){

        if(imagenes!=null && !imagenes.isEmpty()){

            return imagenes.get(0).getUrl();
        }

        return "default.png";
    }
}
