package com.example.sd40.service.MailService;

import com.example.sd40.entity.Mail.MailStructure;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(MailStructure mailStructure) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("lamnhph23964@fpt.edu.vn");
        helper.setTo(mailStructure.getToEmail());
        helper.setSubject(mailStructure.getSubject());
        helper.setText(mailStructure.getMessage());
        javaMailSender.send(message);
    }
}
