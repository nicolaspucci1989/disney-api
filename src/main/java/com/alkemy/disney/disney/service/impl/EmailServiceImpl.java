package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

  @Autowired
  private Environment env;

  @Value("${disney.email.sender}")
  private String emailSender;

  @Value("${disney.email.enabled}")
  private boolean enabled;

  @Override
  public void sendWelcomeEmailTo(String to) {
    if (!enabled) {
      return;
    }

    Email fromEmail = new Email(emailSender);
    Email toEmail = new Email(to);
    String subject = "Welcome";
    Content content = new Content(
        "text/plain",
        "Account successfully created"
    );
    Mail mail = new Mail(fromEmail, subject, toEmail, content);

    SendGrid sg = new SendGrid(env.getProperty("SENDGRID_API_KEY"));
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);

      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      log.error("Error sending email: {}", ex.getMessage());
    }
  }
}
