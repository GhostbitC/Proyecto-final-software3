package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdministradorBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private Administrador administrador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @PostConstruct
    public void inicializar() {
        this.administrador = obtenerAdministrador();
    }

    /***
     * Metodo para obtener un administrador
     * @return El administrador que se requiere
     */
    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                //administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getCedula());
                personaLogin.toString();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return administradorEncontrado;
    }

}
