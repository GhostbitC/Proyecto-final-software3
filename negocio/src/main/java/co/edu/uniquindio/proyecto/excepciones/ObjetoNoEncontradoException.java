package co.edu.uniquindio.proyecto.excepciones;

import java.util.NoSuchElementException;

public class ObjetoNoEncontradoException extends NoSuchElementException {

    public ObjetoNoEncontradoException(String error){
        super(error);
    }
}
