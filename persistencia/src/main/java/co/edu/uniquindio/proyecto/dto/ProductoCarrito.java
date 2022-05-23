package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private Integer id;
    private Integer unidades;
    private String nombre;
    private String imagen;
    private Float precio;

    public ProductoCarrito(Integer id, String nombre, String imagen, Integer unidades, Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.unidades = unidades;
        this.precio = precio;
    }
}
