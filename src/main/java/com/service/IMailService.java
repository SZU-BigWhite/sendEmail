package com.service;

import javax.mail.MessagingException;

public interface IMailService {
    void sendEmail(String to,String subject,String content);
    void sendHtmlEmail(String to,String subject,String content) throws MessagingException;
    void sendAttachEmail(String to,String subject,String content,String path) throws MessagingException;
}
