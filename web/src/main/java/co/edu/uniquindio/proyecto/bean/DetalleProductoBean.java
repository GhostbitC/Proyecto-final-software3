package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("#{param['producto']}")
    private  String idproducto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ReseniaServicio reseniaServicio;

    @Getter
    @Setter
    private Comentario reseniaNueva;

    @Getter @Setter
    private int calificacionPromedio;

    @Getter @Setter
    private List<Comentario> reseniasDetal;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Producto productoAux;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<String>urlImagenes;

    @PostConstruct
    public void inicializar(){

        this.productos = productoServicio.listarProductos();
        this.usuario = new Usuario();
        this.productoAux = new Producto();
        this.reseniaNueva = new Comentario();

        if (idproducto!=null && !"".equals(idproducto)){
            try {
                int id = Integer.parseInt(idproducto);

                this.producto = productoServicio.obtenerProducto(id);
                this.reseniasDetal =  obtenerResenias();
                this.urlImagenes = new ArrayList<>();

                List<Imagen>imagenes = producto.getImagenes();

                if(imagenes.size()>0){

                    for(Imagen i:imagenes){

                        urlImagenes.add(i.getUrl());
                    }
                }else{

                    urlImagenes.add("default.png");
                }

               List<Producto>productosAux=new ArrayList<>();
                productosAux.add(producto);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String irADetalle(int idProducto){
        return  "/detalleProducto?faces-redirect=true&amp;producto="+idProducto;

    }


    public void adquirirProducto() {

        Producto productoEncontrado;

        if(personaLogin!= null){

            try {

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());
                productoEncontrado = productoServicio.obtenerProductoNombre(producto.getNombre());

                usuarioServicio.adquirirProducto(productoEncontrado,usuario.getNombre(),usuario.getId(),usuario.getNumeroTarjeta());


                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! has adquirido el producto");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }

    public List<Comentario> obtenerResenias(){

        List<Comentario> resenias;

        if (idproducto!=null){

            int id = Integer.parseInt(idproducto);

            try {
                resenias = reseniaServicio.obtenerReseniasProducto(id);

                return resenias;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }


    public String ingresarResenia(){

        Producto productoEncontrado;

        try {
            int id = Integer.parseInt(idproducto);
            productoEncontrado = productoServicio.obtenerProducto(id);

            productoServicio.ingresarResenia(reseniaNueva,productoEncontrado);
            this.reseniaNueva = new Comentario();

            return "detalleProducto?faces-redirect=true&amp;producto="+idproducto;

        }catch (Exception e){
            e.printStackTrace();
        }

        return "detalleProducto?faces-redirect=true&amp;producto="+idproducto;

    }


}
