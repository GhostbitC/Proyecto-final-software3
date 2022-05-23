package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//================================= RELACION DE HERENCIA =================================//

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Persona {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACIÓN =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 10)
    @Size(max = 10,message = "El valor ingresado excede los 10 caracteres")
    private String cedula;

    @Column(length = 100,nullable = false)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String nombre;

    @Column(length = 100,nullable = false)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String apellido;

    @Column(length = 100,nullable = false,unique = true)
    private String nickname;

    @Column(length = 100,nullable = false)
    private String password;

    @Column(length = 100,nullable = false,unique = true)
    private String email;

    //================================= CONSTRUCTOR  =================================//
    public Persona(String nombre, String apellido,String nickname, String password, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }


}
