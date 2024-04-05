package com.example.sd40.controller.MailController;

import com.example.sd40.entity.Mail.MailStructure;
import com.example.sd40.service.MailService.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailStructure mailStructure) throws MessagingException {

        mailService.sendMail(mailStructure);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
