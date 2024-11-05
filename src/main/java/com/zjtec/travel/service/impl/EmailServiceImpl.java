package com.zjtec.travel.service.impl;

import com.zjtec.travel.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private final static Logger log= LoggerFactory.getLogger(EmailServiceImpl.class);

    //注意此处使用了暴力定义，导入错误问题仍未解决，待验证
    @Value("${email.smtphost:smtp.sina.cn}")
    private String smtpHost;//SMTP 地址，例如smtp.139.com

    @Value("${email.username:13420015738@sina.cn}")
    private String username;//发送方邮箱地址

    @Value("${email.password:9bdbe3e6bac7a2ea}")
    private String password;//密码或授权码

    @Value("${email.smtpauth:true}")
    private String smtpAuth;//设置为true

    /**
     * 发送邮件
     * @param sendTo 对方邮箱地址
     * @param title 邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendEmail(String sendTo, String title, String content) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", smtpAuth);
            props.put("mail.smtp.host", smtpHost);
            // 发件人的账号
            props.put("mail.user", username);
            //发件人的密码
            props.put("mail.password", password);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(sendTo);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }
}
