package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VentaProductoUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    //================================= RELACIÓN CON LA ENTIDAD ENVÍO =================================//
    @ManyToOne
    @ToString.Exclude
    private Envio envio;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO USUARIO =================================//
    @OneToMany(mappedBy = "ventaProductoUsuario")
    @ToString.Exclude
    private List<ProductoUsuario> productos;
}
