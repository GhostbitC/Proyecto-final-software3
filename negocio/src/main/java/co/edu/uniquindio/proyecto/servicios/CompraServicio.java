package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.util.*;

public interface CompraServicio {

    void crearCompra(Compra c) throws ObjetoNoEncontradoException;

    void agregarCompra(ArrayList<ProductoCarrito> productoCarrito, Usuario usuario, String medioPago) throws ObjetoNoEncontradoException;

    void agregarComprobanteCompra(int idCompra, ComprobantePago comprobantePago) throws ObjetoNoEncontradoException ;

    List<Compra> listarComprasUsuarioSinComprobante(int idUsuario);

    List<Compra> listarComprasSinAprobarUsuarios();

    List<Compra> listarComprasUsuario (int idUsuario);

    Compra obtenerCompraUsuario(int idUsuario,int idCompra) throws ObjetoNoEncontradoException;

}
