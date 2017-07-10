package com.audio.core.service.mail;

import com.audio.core.entity.Mail;

/**
 * Created by taiguorenyao on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
public interface MailService {
    void sendEmail(Mail mail) throws Exception;
}
