package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.EspecificacionRepo;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.servicios.CategoriaProductoServicio;
import co.edu.uniquindio.proyecto.servicios.EspecificacionServicio;
import co.edu.uniquindio.proyecto.servicios.ImagenServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    private final ImagenServicio imagenServicio;

    private final CategoriaProductoServicio categoriaServicio;

    private final ProductoServicio productoServicio;

    private final EspecificacionServicio especificacionServicio;

    private final ImagenRepo imagenRepo;

    private final EspecificacionRepo especificacionRepo;

    private static final String MENSAJEPERSONALIZADO = "mensajePersonalizado";

    private static final String ALERTA = "Alerta";

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
    @Getter @Setter
    private List<Imagen> imagenes;
    @Getter @Setter
    private List<Categoria> categorias;
    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;
    @Getter @Setter
    private String linkImagen;

    public ProductoBean(ImagenServicio imagenServicio, CategoriaProductoServicio categoriaServicio, ProductoServicio productoServicio, EspecificacionServicio especificacionServicio, ImagenRepo imagenRepo, EspecificacionRepo especificacionRepo) {
        this.imagenServicio = imagenServicio;
        this.categoriaServicio = categoriaServicio;
        this.productoServicio = productoServicio;
        this.especificacionServicio = especificacionServicio;
        this.imagenRepo = imagenRepo;
        this.especificacionRepo = especificacionRepo;
    }

    @PostConstruct
    public void inicializar() {
        this.linkImagen="";
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

    public void subirImagenes(){

        if(linkImagen!=null && !linkImagen.isEmpty()){

            Imagen foto = new Imagen(linkImagen);
            imagenes.add(foto);
        }
    }

    public String registrarProducto() {

        try {
            if (seguridadBean.getRol().equals("admin") && !imagenes.isEmpty() && !especificaciones.isEmpty()) {

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
                    productoCreado.setEstado(true);
                    productoServicio.actualizarProducto(productoCreado);
                    this.teclados = obtenerTeclados();
                    this.mouses = obtenerMouses();
                    this.audifonos = obtenerAudifonos();
                    this.portatiles = obtenerPortatiles();

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, ALERTA, "¡Super! el producto se creo correctamente");
                    FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

                return "/administrador/perfilAdministrador?faces-redirect=true";

            } else if (personaLogin!=null) {

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

                        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, ALERTA, "¡Super! el producto se creo correctamente");
                        FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

                return "/usuario/perfilUsuario?faces-redirect=true";

                }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }


    public String actualizarProducto() {

        if (personaLogin != null) {

            try {

                Producto productoActualizado = productoServicio.obtenerProductoNombre(productoN.getNombre());

                imagenRepo.deleteAll(productoActualizado.getImagenes());
                especificacionRepo.deleteAll(productoActualizado.getEspecificaciones());
                productoActualizado.getImagenes().clear();
                productoActualizado.getEspecificaciones().clear();

                for (Imagen i : imagenes) {
                    i.setProducto(productoActualizado);
                    imagenServicio.registrarImagen(i);
                }

                for (Especificacion e : especificaciones) {
                    e.setProducto(productoActualizado);
                    especificacionServicio.registrarEspecificacion(e);
                }

                productoActualizado.setEspecificaciones(especificaciones);
                productoActualizado.setImagenes(imagenes);

                productoServicio.actualizarProducto(productoActualizado, productoN.getNombre());

                this.teclados = obtenerTeclados();
                this.mouses = obtenerMouses();
                this.audifonos = obtenerAudifonos();
                this.portatiles = obtenerPortatiles();

                if (seguridadBean.getRol().equals("admin")){
                    return "/administrador/perfilAdministrador?faces-redirect=true";
                }else{
                    return "/usuario/perfilUsuario?faces-redirect=true";
                }

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, "No pudimos actualizar el producto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
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

    public List<Producto> obtenerTeclados(){
        List<Producto> listaTeclados;
        listaTeclados = productoServicio.listarTeclados();
        return listaTeclados;
    }

    public List<Producto> obtenerMouses(){
        List<Producto> listaMouses;
        listaMouses = productoServicio.listarMouses();
        return listaMouses;
    }

    public List<Producto> obtenerAudifonos(){
        List<Producto> listaAudifonos;
        listaAudifonos = productoServicio.listarAudifonos();
        return listaAudifonos;
    }

    public List<Producto> obtenerPortatiles(){
        List<Producto> listaPortatiles;
        listaPortatiles = productoServicio.listarPortatiles();
        return listaPortatiles;
    }
}
