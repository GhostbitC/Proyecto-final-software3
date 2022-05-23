package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 100,nullable = false)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "categoria")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACIÓN CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    public Categoria(String nombre, String descripcion, Administrador administrador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
        this.administrador = administrador;
    }
}
