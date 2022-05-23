package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Administrador extends Persona {

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO USUARIO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Producto> productosAprobados;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private transient List<Compra> compras;

    //================================= CONSTRUCTOR  =================================//
    public Administrador(String nombre,String apellido, String nickname, String password, String email) {
        super(nombre,apellido, nickname, password, email);
        productos= new ArrayList<>();
        productosAprobados = new ArrayList<>();
    }


}
