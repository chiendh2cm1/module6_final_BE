package com.codegym.module6_be.service.email;

import com.codegym.module6_be.model.User;
import com.codegym.module6_be.model.Workspace;
import com.codegym.module6_be.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserService userService;
    public void sendVerificationEmail(User user, User sender, Workspace workspace)
            throws MessagingException {
        String subject = "Lời mời cộng tác";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getUsername() + ",</p>";
        mailContent += "<p> Bạn đã được " + sender.getUsername() + " mời vào nhóm " + workspace.getTitle();
        mailContent += "<br><a href ='http://localhost:4200/login'> Đăng nhập </a>vào hệ thống " ;
        mailContent += "và truy cập vào nhóm tại <a href='http://localhost:52710/login?returnUrl=%2F"+workspace.getId()+"/home'>đây</a> ";
        mailContent += "</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom(senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }
}
