package com.example.sd40.entity.Mail;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailStructure {
    private String subject;
    private String message;
    private String toEmail;
}
