package com.vn.ecommercebe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
// This service is used to send emails for password reset requests.
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    public void constructEmail(String toMailAdr) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage,true,"utf-8");
        String text = "<p style=\"color: black\">Một file đính kèm đã được gửi tới email này!</p>\n";

        text += "<br/>\n";
        String body = text;
        String subject = "File export attached";
        email.setSubject(subject);
        email.setText(body,true);
        email.setTo(toMailAdr);
        email.setFrom("ngovanthuong12369@gmail.com");
//        MimeBodyPart bodyPart = new MimeBodyPart();
//        File file = new File("C:\\Users\\ngova\\Downloads\\output.docx");

        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir, "output.docx");
        email.addAttachment(file.getName(),file);
        mailSender.send(mimeMessage);
    }



}
