package com.dba.service;

/**
 * Author:huangjb
 * Date:2018/7/29
 * Description:
 */
public interface MailService {
    void sendMail(String title, String url, String email);

    void regietrNotify(String phone);

    boolean activeUser(String key);
}
