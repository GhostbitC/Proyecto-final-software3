package co.edu.uniquindio.proyecto.entidades;

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
public class CategoriaProducto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    private String nombre;

    @Column(name = "descripcion",length = 200)
    private String descripcion;

    //================================= RELACION CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    public CategoriaProducto(String nombre, String descripcion, Administrador administrador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
        this.administrador = administrador;
    }

    public CategoriaProducto(String nombre, Administrador administrador) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
        this.administrador = administrador;
    }
}
