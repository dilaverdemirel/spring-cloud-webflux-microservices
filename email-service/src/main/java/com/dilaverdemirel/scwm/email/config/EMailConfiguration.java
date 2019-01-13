package com.dilaverdemirel.scwm.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author dilaverd
 */
@Configuration
public class EMailConfiguration {
    @Value("${spring.mail.protocol}")
    private String mailServerProtocol;

    @Value("${spring.mail.host}")
    private String mailServerHost;

    @Value("${spring.mail.port}")
    private Integer mailServerPort;

    @Value("${spring.mail.username}")
    private String mailServerUserName;

    @Value("${spring.mail.password}")
    private String mailServerPassword;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String mailSmtpFrom;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String mailTransportProtocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.properties.mail.smtp.host}")
    private String mailSmtpHost;

    @Value("${spring.mail.properties.mail.smtp.port}")
    private String mailSmtpPort;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String mailSmtpSocketFactory;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(mailServerProtocol);
        javaMailSender.setHost(mailServerHost);
        javaMailSender.setPort(Integer.valueOf(mailServerPort));
        javaMailSender.setUsername(mailServerUserName);
        javaMailSender.setPassword(mailServerPassword);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.from", mailSmtpFrom);
        properties.setProperty("mail.transport.protocol", mailTransportProtocol);
        properties.setProperty("mail.smtp.auth", mailSmtpAuth);
        properties.setProperty("mail.smtp.host", mailSmtpHost);
        properties.setProperty("mail.smtp.port", mailSmtpPort);
        properties.setProperty("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        properties.setProperty("mail.smtp.socketFactory.class", mailSmtpSocketFactory);

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }
}
