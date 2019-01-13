package com.dilaverdemirel.scwm.email.service;

import com.dilaverdemirel.scwm.email.dto.EmailEventDTO;
import com.dilaverdemirel.scwm.email.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author dilaverd
 */
@Service
public class EMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, EmailEventDTO emailEventDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("order@order-system.com");
        message.setSubject(emailEventDTO.subject);
        if(emailEventDTO.content != null) {
            message.setText(emailEventDTO.content);
        } else {
            message.setText("---");
        }
        javaMailSender.send(message);
    }
}
