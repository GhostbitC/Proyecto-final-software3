package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto",nullable = false)
    @EqualsAndHashCode.Include
    private int codigoProducto;

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
    @OneToMany(mappedBy = "producto",fetch=FetchType.EAGER)
    @ToString.Exclude
    private List<Imagen> imagenes;

    //================================= RELACIÓN CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Favorito> favoritos;

    public Producto(String nombre, String descripcion, double precio, Administrador administrador, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.administrador = administrador;
        this.categoria = categoria;
    }

    public String getImagenPrincipal(){

        if(imagenes!=null && !imagenes.isEmpty()){

            return imagenes.get(0).getUrl();
        }

        return "default.png";
    }
}
