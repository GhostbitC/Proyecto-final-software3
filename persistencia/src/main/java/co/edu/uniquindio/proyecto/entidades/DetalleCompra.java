package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {

    //---------------------------------- ATRIBUTOS ----------------------
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Positive
    @Column(name = "precio_producto",nullable = false)
    private Float precioProducto;

    //---------------------------------- RELACIONES ----------------------
    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Compra compra;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Producto producto;

    //---------------------------------- CONSTRUCTOR ----------------------
    public DetalleCompra(Integer unidades, Float precioProducto, Producto producto, Compra compra) {
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.compra = compra;
    }

}
