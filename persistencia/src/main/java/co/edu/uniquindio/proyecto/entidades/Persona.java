package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

//================================= RELACION DE HERENCIA =================================//

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Persona implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACIÓN =================================//
    @Id
    @Column(name = "cedula", length = 10)
    @Size(max = 10,message = "El valor ingresado excede los 10 caracteres")
    @EqualsAndHashCode.Include
    private String cedula;

    @Column(name = "nombre",length = 100,nullable = false)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String nombre;

    @Column(name = "apellido",length = 100,nullable = false)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String apellido;

    @Column(name = "nickname",length = 100,nullable = false,unique = true)
    private String nickname;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    //================================= CONSTRUCTOR  =================================//
    public Persona(String cedula, String nombre, String nickname, String password, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }


}
