package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Administrador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD TRABAJADOR =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Trabajador> trabajadores;

    //================================= RELACION CON LA ENTIDAD SERVICIO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Servicio> servicios;

    //================================= RELACION CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACION CON LA ENTIDAD CATEGORIA =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<CategoriaProducto> categorias;

    //================================= CONSTRUCTOR  =================================//
    public Administrador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        trabajadores = new ArrayList<>();
        servicios= new ArrayList<>();
        productos= new ArrayList<>();
        categorias= new ArrayList<>();
    }


}
