package com.audio.controller.base;

import com.audio.core.entity.Mail;
import com.audio.core.service.mail.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by taiguorenyao on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
@Controller
@RequestMapping("/base")
public class MailController {

    @Resource
    private MailService mailService;

    @RequestMapping("/sendMail")
    public String sendMail(){
        Mail mail = new Mail();
        try {
            mailService.sendEmail(null);
        }catch (Exception e){
            System.out.println("邮件发送失败！！"+e);
        }
        return null;
    }
}
