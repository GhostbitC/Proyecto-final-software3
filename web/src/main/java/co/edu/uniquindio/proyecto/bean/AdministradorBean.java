package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.*;

@Component
@ViewScoped
public class AdministradorBean {

    private final AdministradorServicio administradorServicio;

    private final CompraServicio compraServicio;

    private final ProductoServicio productoServicio;

    @Getter @Setter
    private Administrador administrador;

    @Getter
    @Setter
    private List<Producto>productosSinAprobarUsuarios;

    @Getter
    @Setter
    private List<Producto>productosPublicados;

    @Getter
    @Setter
    private List<Compra>comprasUsuariosSinAprobar;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public AdministradorBean(AdministradorServicio administradorServicio, CompraServicio compraServicio, ProductoServicio productoServicio) {
        this.administradorServicio = administradorServicio;
        this.compraServicio = compraServicio;
        this.productoServicio = productoServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.administrador = obtenerAdministrador();
        this.comprasUsuariosSinAprobar = obtenerComprasSinValidar();
        this.productosSinAprobarUsuarios = obtenerProductosSinAprobar();
        this.productosPublicados = obtenerProductosPublicados();
    }

    /***
     * Metodo para obtener un administrador
     * @return El administrador que se requiere
     */
    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getId());
                //personaLogin.toString();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return administradorEncontrado;
    }

    public void aprobarProductoUsuario(int idProducto) throws Exception {

        administradorServicio.aprobarProductoUsuario(idProducto, personaLogin.getId());
        this.productosSinAprobarUsuarios = obtenerProductosSinAprobar();
    }

    public void rechazarProductoUsuario(int idProducto) throws Exception {

        administradorServicio.rechazarProductoUsuario(idProducto, personaLogin.getId());
        this.productosSinAprobarUsuarios = obtenerProductosSinAprobar();
    }

    public void aprobarCompra(int idCompra){

        administradorServicio.aprobarCompra(idCompra, personaLogin.getId());
        this.comprasUsuariosSinAprobar = obtenerComprasSinValidar();
    }

    public void eliminarProducto(int idProducto) throws Exception {

        if (personaLogin!=null){
            productoServicio.eliminarProducto(idProducto);
            this.productosPublicados = obtenerProductosPublicados();
        }
    }

    public void rechazarCompra(int idCompra){

        administradorServicio.rechazarCompra(idCompra, personaLogin.getId());
        this.comprasUsuariosSinAprobar = obtenerComprasSinValidar();
    }


    public List<Producto> obtenerProductosSinAprobar(){

        List<Producto> productos= new ArrayList<>();

        if (personaLogin!=null){
            productos=productoServicio.listarProductosSinAprobarUsuarios();
        }

        return productos;
    }

    public List<Compra> obtenerComprasSinValidar(){

        List<Compra> compras = new ArrayList<>();

        if (personaLogin!=null){
            compras = compraServicio.listarComprasSinAprobarUsuarios();
        }

        return compras;
    }

    public List<Producto> obtenerProductosPublicados(){

        List<Producto> productos = new ArrayList<>();

        if (personaLogin!=null){
            productos =productoServicio.listarProductosAdmin(personaLogin.getId());
        }

        return productos;
    }

}
