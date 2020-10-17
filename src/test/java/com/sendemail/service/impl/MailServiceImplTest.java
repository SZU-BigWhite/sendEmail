package com.sendemail.service.impl;

import com.sendemail.SendemailApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

public class MailServiceImplTest extends SendemailApplicationTests {

    @Autowired
    MailServiceImpl mailService;

    @Autowired
    TemplateEngine templateEngine;
    static final String to="909610728@qq.com";
    @Test
    public void sendEmail() {
        mailService.sendEmail(to,"first email","这是第一封邮件");
    }

    @Test
    public void sendHtmlEmail() {
        String content="<html>\n"+
                "<body>\n"+
                    "<h1>这是一封html邮件</h1>"+
                "</body>\n"+
                "</html>";
        try {
            mailService.sendHtmlEmail(to,"first email",content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachEmail() {
        String content="<html>\n"+
                "<body>\n"+
                "<h1>这是一封html邮件</h1>"+
                "</body>\n"+
                "</html>";
        String filePath="E:\\attach.txt";
        try {
            mailService.sendAttachEmail(to,"first email",content,filePath);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void sendEmailTemplate(){
        Context context=new Context();
        context.setVariable("text","点击跳转");

        String content=templateEngine.process("emailTemplate",context);
        try {
            mailService.sendHtmlEmail(to,"template Email",content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}