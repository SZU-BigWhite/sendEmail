package com.service.impl;

import com.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements IMailService {

    @Value("${spring.mail.username}")
    String from;
    @Autowired
    JavaMailSender mailSender;

    //发送普通类型邮件
    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        System.out.println(from);
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

    //发送html类型邮件
    @Override
    public void sendHtmlEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);

        mailSender.send(mimeMessage);
    }

    //发送带附件类型邮件
    @Override
    public void sendAttachEmail(String to, String subject, String content,String filePath) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);

        FileSystemResource resource=new FileSystemResource(filePath);
        helper.addAttachment(resource.getFilename(),resource);

        mailSender.send(mimeMessage);
    }
}
