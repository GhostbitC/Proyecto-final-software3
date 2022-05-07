package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CategoriaProductoServicio;
import co.edu.uniquindio.proyecto.servicios.EspecificacionServicio;
import co.edu.uniquindio.proyecto.servicios.ImagenServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import com.cloudinary.utils.ObjectUtils;
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cloudinary.*;

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

    @Getter @Setter
    private Categoria categoria;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    @Value(value = "#{seguridadBean}")
    private SeguridadBean seguridadBean;

    @Getter @Setter
    private Producto productoN;

    @Getter @Setter
    private List<Producto> teclados;

    @Getter @Setter
    private List<Producto> mouses;

    @Getter @Setter
    private List<Producto> audifonos;

    @Getter @Setter
    private List<Producto> portatiles;
    @Getter @Setter
    private List<Especificacion> especificaciones;
    @Getter @Setter
    private Especificacion especificacion;
    @Value("${upload.url}")
    private String urlImagenes;

    private ArrayList<Imagen> imagenes;
    @Getter @Setter
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
        this.teclados = obtenerTeclados();
        this.mouses = obtenerMouses();
        this.audifonos = obtenerAudifonos();
        this.portatiles = obtenerPortatiles();
    }

    public void subirImagenes(FileUploadEvent event) throws IOException {

        UploadedFile imagen = event.getFile();

//        System.out.println(imagen.getFileName());
//
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dtz8xwoku",
//                "api_key", "226879525723834",
//                "api_secret", "buFX4wXU7iUa3R_A2YRLnuxuQ_U"));
//
//        Map uploadResult = cloudinary.uploader().upload(new File("doc.mp4"), ObjectUtils.emptyMap());
//
//        System.out.println(uploadResult.get("url"));

        String nombreImagen = subirImagen(imagen);

        if (nombreImagen != null) {

            Imagen foto = new Imagen(nombreImagen);

            imagenes.add(foto);
        }
    }

    public String subirImagen(UploadedFile file) {

        try {
            InputStream input = file.getInputStream();
            System.out.println("Entro 1");
            String fileName = FilenameUtils.getName(file.getFileName());
            System.out.println("Entro 2");
            String baseName = FilenameUtils.getBaseName(fileName) + "_";
            System.out.println("Entro 3");
            String extension = "." + FilenameUtils.getExtension(fileName);
            System.out.println("Entro 4");
            File fileDest = File.createTempFile(baseName, extension, new File(urlImagenes));
            System.out.println("Entro 5");
            FileOutputStream output = new FileOutputStream(fileDest);
            System.out.println("Entro 6");
            IOUtils.copy(input, output);
            System.out.println("Entro 7");

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
                    producto.setEstado(true);
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

                    this.teclados = obtenerTeclados();
                    this.mouses = obtenerMouses();
                    this.audifonos = obtenerAudifonos();
                    this.portatiles = obtenerPortatiles();

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

                        this.teclados = obtenerTeclados();
                        this.mouses = obtenerMouses();
                        this.audifonos = obtenerAudifonos();
                        this.portatiles = obtenerPortatiles();

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


    public void actualizarProducto() {

        if (personaLogin != null) {

            try {

                productoServicio.actualizarProducto(producto, productoN.getNombre());

                this.teclados = obtenerTeclados();
                this.mouses = obtenerMouses();
                this.audifonos = obtenerAudifonos();
                this.portatiles = obtenerPortatiles();

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto se actualizo correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No pudimos actualizar el producto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }

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

    public List<Producto> obtenerTeclados(){

        List<Producto> teclados = new ArrayList<>();

        teclados = productoServicio.listarTeclados();

        System.out.println(teclados.size());

        return teclados;
    }

    public List<Producto> obtenerMouses(){

        List<Producto> mouses = new ArrayList<>();

        mouses = productoServicio.listarMouses();

        return mouses;
    }

    public List<Producto> obtenerAudifonos(){

        List<Producto> audifonos = new ArrayList<>();

        audifonos = productoServicio.listarAudifonos();

        return audifonos;
    }

    public List<Producto> obtenerPortatiles(){

        List<Producto> portatiles = new ArrayList<>();

        portatiles = productoServicio.listarPortatiles();

        return portatiles;
    }

}
