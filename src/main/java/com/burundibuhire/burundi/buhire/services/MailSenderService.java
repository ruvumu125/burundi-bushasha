package com.burundibuhire.burundi.buhire.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class MailSenderService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public MailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendNewMail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("info@solverexpress.com"); // your Gmail email address

        mailSender.send(message);
    }

    public void sendVerificationEmai(String to, String token,String name, String password) {

        String subject = "Verify your email";
        String verificationLink = "http://localhost:8084/members/burundibushasha/v1/verify/" + token;
        //String body = "Click the link to verify your email: " + verificationLink;
        String content = getHtmlContent(name, password);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom("info@solverexpress.com");

        mailSender.send(message);
    }

    public void sendEmail(String to, String subject, Map<String, Object> templateData, String templateName) throws MessagingException, MessagingException {

        // Create MimeMessage instance
        MimeMessage message = mailSender.createMimeMessage();

        // Create MimeMessageHelper instance to handle the message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Prepare the context for Thymeleaf
        Context context = new Context();
        context.setVariables(templateData);

        // Process the template to generate HTML content
        String htmlContent = templateEngine.process(templateName, context);

        // Set the necessary fields in the email message
        helper.setFrom("info@solverexpress.com");  // Set the 'from' address
        helper.setTo(to);      // Set the recipient's email address
        helper.setSubject(subject);  // Set the email subject
        helper.setText(htmlContent, true);  // Set the email content in HTML format

        // Send the email
        mailSender.send(message);
    }

    private String getHtmlContent(String name, String password) {
        return """
        <!doctype html>
        <html>
          <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Account Creation Confirmation</title>
            <style>
              body {
                background-color: #f6f6f6;
                font-family: sans-serif;
                margin: 0;
                padding: 0;
                line-height: 1.4;
              }
              table {
                border-collapse: separate;
                width: 100%;
              }
              .container {
                max-width: 580px;
                margin: 0 auto;
                padding: 10px;
              }
              .content {
                background: #ffffff;
                border-radius: 5px;
                padding: 20px;
              }
              .footer {
                text-align: center;
                margin-top: 20px;
                font-size: 12px;
                color: #999999;
              }
              h1 {
                text-align: center;
                color: #333333;
              }
              p {
                font-size: 14px;
                color: #555555;
                line-height: 1.6;
                margin: 10px 0;
              }
              a {
                color: #3498db;
                text-decoration: none;
              }
              .btn {
                display: inline-block;
                background-color: #3498db;
                color: #ffffff;
                text-decoration: none;
                padding: 10px 20px;
                border-radius: 5px;
                margin: 10px 0;
              }
            </style>
          </head>
          <body>
            <table role="presentation" class="container">
              <tr>
                <td>
                  <div class="content">
                    <h1>Bienvenue sur xxxx</h1>
        """ +
                "<p>Chèr(e) " + name + ",</p>" +  // Inline concatenation here
                """
                            <p>
                              Nous sommes heureux de vous annoncer que votre compte sur <b>xxxx</b> a été créé avec succès. 
                              Vous pouvez vous connecter en utilisant les informations suivantes :
                            </p>
                """ +
                "<p><b>Mot de passe :</b> " + password + "</p>" +  // Inline concatenation here
                """
                            <p>
                              <a href="https://example.com/login" class="btn">Se connecter</a>
                            </p>
                            <p>Cordialement,<br>L’équipe Ligne Web Services</p>
                            <p><em>Ceci est un mail automatique, veuillez ne pas y répondre.</em></p>
                          </div>
                          <div class="footer">
                            <p>Company Inc, 3 Abbey Road, San Francisco CA 94102</p>
                            <p>Ne souhaitez plus recevoir ces e-mails ? <a href="#">Se désabonner</a>.</p>
                            <p>Powered by <a href="http://htmlemail.io">HTMLemail</a>.</p>
                          </div>
                        </td>
                      </tr>
                    </table>
                  </body>
                </html>
                """;
    }


}

