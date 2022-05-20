package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    private final ProductoServicio productoServicio;

    private final ComentarioServicio comentarioServicio;

    @Value("#{param['producto']}")
    private String idProducto;

    @Getter
    @Setter
    private Producto producto;

    @Getter @Setter
    private int calificacionPromedio;

    @Getter @Setter
    private int porcentaje1;

    @Getter @Setter
    private int porcentaje2;

    @Getter @Setter
    private int porcentaje3;

    @Getter @Setter
    private int porcentaje4;

    @Getter @Setter
    private int porcentaje5;

    @Getter
    @Setter
    private Persona personaCreadora;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private List<String>urlImagenes;

    @Getter @Setter
    private int[] promedios;

    @Getter @Setter
    private List<Comentario> comentariosDetal;

    @Getter @Setter
    private Comentario comentarioNuevo;

    public DetalleProductoBean(ProductoServicio productoServicio, ComentarioServicio comentarioServicio) {
        this.productoServicio = productoServicio;
        this.comentarioServicio = comentarioServicio;
    }

    @PostConstruct
    public void inicializar() {

        this.comentarioNuevo = new Comentario();

        if (idProducto!=null && !"".equals(idProducto)){
            try {
                int id = Integer.parseInt(idProducto);
                this.producto = productoServicio.obtenerProducto(id);
                this.urlImagenes = new ArrayList<>();
                this.calificacionPromedio = productoServicio.obtenerCalificacionPromedio(id);
                this.promedios= productoServicio.obtenerPorcentaje(id);
                this.porcentaje1 = promedios[0];
                this.porcentaje2 = promedios[1];
                this.porcentaje3 = promedios[2];
                this.porcentaje4 = promedios[3];
                this.porcentaje5 = promedios[4];
                this.comentariosDetal = obtenerComentarios();

                List<Imagen> imagenes = producto.getImagenes();

                if(imagenes.size()>0){
                    for(Imagen i:imagenes){
                        urlImagenes.add(i.getUrl());
                    }
                }else{
                    urlImagenes.add("default.png");
                }
                personaCreadora = obtenerPersonaCreadora(id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Persona obtenerPersonaCreadora(int idProducto) {

        Persona aux;

        if (producto.getUsuario() !=null){

            aux = producto.getUsuario();
        }else{
            aux = producto.getAdministrador();
        }
        return aux;
    }

    public List<Comentario> obtenerComentarios(){

        List<Comentario> comentarios;

        if (idProducto!=null){

            int id = Integer.parseInt(idProducto);

            try {
                comentarios = comentarioServicio.obtenerComentariosProducto(id);

                return comentarios;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

    public String ingresarComentario(){

        Producto productoEncontrado;

        try {
            int id = Integer.parseInt(idProducto);
            productoEncontrado = productoServicio.obtenerProducto(id);

            if (personaLogin!=null && productoEncontrado!=null){
                productoServicio.ingresarComentario(comentarioNuevo,productoEncontrado,personaLogin);
                this.comentariosDetal.add(comentarioNuevo);
                this.comentarioNuevo = new Comentario();
                this.calificacionPromedio = productoServicio.obtenerCalificacionPromedio(id);
                this.promedios= productoServicio.obtenerPorcentaje(id);
                this.porcentaje1 = promedios[0];
                this.porcentaje2 = promedios[1];
                this.porcentaje3 = promedios[2];
                this.porcentaje4 = promedios[3];
                this.porcentaje5 = promedios[4];

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Tu opinión se registros exitosamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e){
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }
}
