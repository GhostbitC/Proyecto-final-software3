package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.ComprobantePago;

import java.util.List;

public interface ComprobantePagoServicio {

    ComprobantePago registrarComprobante(ComprobantePago com) throws Exception;

    ComprobantePago actualizarComprobante(ComprobantePago com) throws Exception;

    void eliminarComprobante(int id) throws Exception;

    ComprobantePago obtenerComprobante(int id) throws Exception;

    ComprobantePago obtenerImagenComprobanteCompra(int idCompra) throws Exception;

    List<ComprobantePago> listarComprobantes();
}
