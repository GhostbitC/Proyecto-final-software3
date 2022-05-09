package co.edu.uniquindio.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void enviarEmail(String asunto, String contenido, String destinatario){
        MimeMessage mensaje = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setText(contenido,true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setSentDate(new Date());

            sender.send(mensaje);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
