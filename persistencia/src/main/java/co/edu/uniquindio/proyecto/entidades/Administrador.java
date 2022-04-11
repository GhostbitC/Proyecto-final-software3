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

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO USUARIO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<ProductoUsuario> productosAprobadosUsuarios;

    //================================= CONSTRUCTOR  =================================//
    public Administrador(String nombre,String apellido, String nickname, String password, String email) {
        super(nombre,apellido, nickname, password, email);
        productos= new ArrayList<>();
        productosAprobadosUsuarios = new ArrayList<>();
    }


}
