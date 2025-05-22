/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author emdominguez
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    public static void sendEmail(String to, String subject, String body) throws MessagingException {
        final String from = "quiquemachadodguez@gmail.com";            // mi correo
        final String password = "fytd egti vucc lqdt";  // Contraseña o clave de app desde donde envio el correo //CONFIDENCIAL

        // Configuración del servidor SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");                  // Autenticación
        props.put("mail.smtp.starttls.enable", "true");       // Seguridad TLS
        props.put("mail.smtp.host", "smtp.gmail.com");        // Servidor SMTP de Gmail
        props.put("mail.smtp.port", "587");                    // Puerto SMTP de Gmail

        // Crear sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Crear el mensaje de correo
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));                     // De quién
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  // A quién
        message.setSubject(subject);                                    // Asunto
        message.setText(body);                                          // Contenido texto

        // Enviar mensaje
        Transport.send(message);
    }
}

