package co.edu.uniquindio.proyecto.excepciones;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ObjetoNoEncontradoException extends NoSuchElementException implements Serializable {

    public ObjetoNoEncontradoException(String error){
        super(error);
    }
}
