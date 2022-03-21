package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
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
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class MascotaBean implements Serializable {

    private final MascotaServicio mascotaServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoServicio tipoServicio;
    private final ImagenServicio imagenServicio;

    @Getter
    @Setter
    private Mascota mascota;

    @Getter
    @Setter
    private Mascota mascotaN;

    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<Imagen> imagenes;

    @Getter
    @Setter
    private List<TipoMascota> tipos;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public MascotaBean(MascotaServicio lugarServicio, UsuarioServicio usuarioServicio, TipoServicio tipoServicio, ImagenServicio imagenServicio) {
        this.mascotaServicio = lugarServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoServicio = tipoServicio;
        this.imagenServicio = imagenServicio;

    }

    @PostConstruct
    public void inicializar() {
        this.mascota = new Mascota();
        this.mascotaN= new Mascota();
        this.tipos = tipoServicio.listarTipos();
        this.imagenes = new ArrayList<>();

    }

    public String registrarMascota() {
        try {
            if (personaLogin != null) {
                if (!imagenes.isEmpty()){

                    mascota.setUsuario((Usuario) personaLogin);

                    Mascota mascotaCreada = mascotaServicio.registrarMascota(this.mascota);

                    for (Imagen i : imagenes) {
                        i.setMascota(mascotaCreada);
                        imagenServicio.registrarImagen(i);
                    }

                    mascotaCreada.setImagenes(imagenes);

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la mascota se creo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return null;
    }

    public void actualizarMascota() {

        if (personaLogin != null) {

            try {

                mascotaServicio.actualizarMascota(mascota, mascotaN.getNombre());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la mascota se actualizo correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No pudimos actualizar la mascota");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        }

    }

    public void subirImagenes(FileUploadEvent event) {

        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);

        if (nombreImagen != null) {

            Imagen foto = new Imagen(nombreImagen);

            imagenes.add(foto);
        }
    }

    public String subirImagen(UploadedFile file) {

        try {
            InputStream input = file.getInputStream();
            String fileName = FilenameUtils.getName(file.getFileName());
            String baseName = FilenameUtils.getBaseName(fileName) + "_";
            String extension = "." + FilenameUtils.getExtension(fileName);
            File fileDest = File.createTempFile(baseName, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);

            return fileDest.getName();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void eliminarMascota() {

        if (personaLogin != null) {
            try {

                Mascota mascotaAux = mascotaServicio.obtenerMascotaNombre(mascota.getNombre());

                mascotaServicio.actualizarMascota(mascotaAux);

                List<Imagen> imagenes = imagenServicio.obtenerImagenesMascota(mascotaAux.getId());

                for (Imagen i : imagenes) {
                    imagenServicio.eliminarImagen(i.getId());
                }

                mascotaServicio.eliminarMascota(mascotaAux.getNombre());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la mascota se elimino correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No pudimos eliminar la mascota");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

}
