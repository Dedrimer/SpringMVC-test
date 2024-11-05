package com.zjtec.travel.service;

public interface EmailService {
    /**
     * 发送邮件
     * @param sendTo 对方邮箱地址
     * @param title 邮件标题
     * @param content 邮件内容
     */
    void sendEmail(String sendTo, String title, String content);

    /**
     * 设置SMTP主机
     * @param smtpHost SMTP地址
     */
    void setSmtpHost(String smtpHost);

    /**
     * 设置用户名
     * @param username 用户名
     */
    void setUsername(String username);

    /**
     * 设置密码
     * @param password 密码或授权码
     */
    void setPassword(String password);

    /**
     * 设置SMTP认证
     * @param smtpAuth 是否认证
     */
    void setSmtpAuth(String smtpAuth);
}
