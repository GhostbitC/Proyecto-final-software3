package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CategoriaProductoServicio;
import co.edu.uniquindio.proyecto.servicios.EspecificacionServicio;
import co.edu.uniquindio.proyecto.servicios.ImagenServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
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
public class ProductoBean implements Serializable {

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private CategoriaProductoServicio categoriaServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EspecificacionServicio especificacionServicio;

    @Getter
    @Setter
    private Categoria categoria;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    @Value(value = "#{seguridadBean}")
    private SeguridadBean seguridadBean;

    @Getter @Setter
    private Producto productoN;

    @Getter
    @Setter
    private List<Especificacion> especificaciones;

    @Getter @Setter
    private Especificacion especificacion;

    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<Imagen> imagenes;

    @Getter
    @Setter
    private List<Categoria> categorias;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;


    @PostConstruct
    public void inicializar() {
        this.categoria= new Categoria();
        this.producto = new Producto();
        this.productoN= new Producto();
        this.imagenes = new ArrayList<>();
        this.categorias = categoriaServicio.listarCategorias();
        this.especificaciones = new ArrayList<>();
        this.especificacion = new Especificacion();

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

    public String registrarProducto() {

        try {
            if (personaLogin != null && seguridadBean.getRol().equals("admin")) {
                if (!imagenes.isEmpty() && !especificaciones.isEmpty()) {

                    producto.setAdministrador((Administrador) personaLogin);

                    Producto productoCreado = productoServicio.registrarProducto(this.producto);

                    for (Imagen i : imagenes) {
                        i.setProducto(productoCreado);
                        imagenServicio.registrarImagen(i);
                    }

                    for (Especificacion e : especificaciones) {
                        e.setProducto(productoCreado);
                        especificacionServicio.registrarEspecificacion(e);
                    }

                    productoCreado.setEspecificaciones(especificaciones);
                    productoCreado.setImagenes(imagenes);

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el producto se creo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            } else {
                if (personaLogin != null && seguridadBean.getRol().equals("usuario")) {
                    if (!imagenes.isEmpty() && !especificaciones.isEmpty()) {

                        producto.setUsuario((Usuario) personaLogin);

                        Producto productoCreado = productoServicio.registrarProducto(this.producto);

                        for (Imagen i : imagenes) {
                            i.setProducto(productoCreado);
                            imagenServicio.registrarImagen(i);
                        }

                        for (Especificacion e : especificaciones) {
                            e.setProducto(productoCreado);
                            especificacionServicio.registrarEspecificacion(e);
                        }

                        productoCreado.setEspecificaciones(especificaciones);
                        productoCreado.setImagenes(imagenes);

                        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el producto se creo correctamente");
                        FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return null;
    }


    public void nuevaEspecificacion() {
        this.especificacion = new Especificacion();
    }

    public void crearEspecificacion() {

        if (personaLogin != null) {

            this.especificaciones.add(especificacion);
            nuevaEspecificacion();
        }

    }

    public void eliminarEspecificacion() {
        this.especificaciones.remove(this.especificacion);
        nuevaEspecificacion();
    }

}
