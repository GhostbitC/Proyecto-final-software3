package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.CategoriaProductoServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CategoriaProductoServicio categoriaServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            Administrador admin1= new Administrador("Sebastian","Quintero","Botsorio","admin1","admin1@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            Administrador admin2= new Administrador("Braian","Piedrahita","Ghostbit","admin2","admin2@gmail.com");
            administradorServicio.registrarAdministrador(admin2);

            Categoria categoria1 = new Categoria("Mouses","Perifericos",admin1);
            categoriaServicio.registrarCategoria(categoria1);
        }

    }
}
