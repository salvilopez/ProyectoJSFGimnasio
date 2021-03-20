/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

public class mail {

    public static void mandarCorreo(String CorreoDestino, String Asunto, String Cuerpo, HttpServletRequest request) {
        // El correo gmail de envío
        String correoEnvia = "salvihealthygym@gmail.com";
        String claveCorreo = "Buakawporpramuk92?";

        // La configuración para enviar correo
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, null);
        int aviso = 0;
        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "HealthyGym"));

            // Los destinatarios
            InternetAddress[] internetAddresses = {new InternetAddress(CorreoDestino)};
//		     new InternetAddress("correo2@gmail.com"),
//		     new InternetAddress("correo3@gmail.com") };

            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject(Asunto);

            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(Cuerpo);

            MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/images/logo.png");
            mimeBodyPartAdjunto.attachFile(path);

            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(mimeBodyPartAdjunto);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            aviso = 1;
        }
        if (aviso == 0) {
            String resultado = "Correo Enviado correctamente";
            request.setAttribute(resultado, "resultado");
        }
    }

}
