package com.disney.alkemy.services.impl;

import com.disney.alkemy.services.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    /**
     * ES PARA PONER EL API KEY, PORQUE COMO TIENE QUE SER MUY SEGURO, NO TIENE QUE ESTAR EN EL CODIGO
     */
    @Autowired
    private Environment env;
    
    @Value("${Disney.alkemy.email.sender}")
    private String emailSender;
    
    @Value("${Disney.alkemy.email.enabled}")
    private boolean enabled;
    
    public void sendWelcomeEmailTo(String to) {
        if (!enabled) {
            return;
        }
        //EMAIL_API_KEY, agreagda en propeties/accions de este projecto y ademas en configuraciones avanzadas de este equipo, en environment variable
        String apiKey = env.getProperty("EMAIL_API_KEY");
        
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                    "text/plain",
                    "Bienvenido/a a Disney Alkemy"
        );
        String subject = "Disney Alkemy";
        
        //CREO EL MAIL CON EL CONTENIDO HECHO ARRIBA 
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            System.out.println("Error trying to send the email");
        }
    }
}