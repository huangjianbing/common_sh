package com.dba.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.dba.mapper.UserMapper;
import com.dba.pojo.User;
import com.dba.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Author:huangjb
 * Date:2018/7/29
 * Description:
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private  String from;
    @Value("${domain.name}")
    private String domain;
    @Autowired
    private UserMapper userMapper;
    private  final Cache<String,String> registeCache= CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(1, TimeUnit.DAYS).
            removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> removalNotification) {
                    userMapper.delete(removalNotification.getValue());
                }
            }).build();
    @Override
    public void sendMail(String title, String url,String to) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(from);
        message.setText(url);
        message.setSubject(title);
        mailSender.send(message);

    }
    /**
     * 缓存 key-email的关系
     * 借助springmaill发送邮件
     * 异步发送邮件
     * @param phone
     */
    @Async
    public void regietrNotify(String phone) {
        String key= RandomStringUtils.randomAlphabetic(10);
        registeCache.put(key,phone);
        String url="http://"+domain+"/user/activeUser?key="+key;
        sendMail("小程序用户激活邮件",url,from);
    }

    @Override
    public boolean activeUser(String key) {
        String phone = registeCache.getIfPresent(key);
        if(StringUtils.isBlank(phone)){
            return false;
        }
        else {
            registeCache.invalidate(phone);
            User userUpdate=new User();
            userUpdate.setPhone(phone);
            userUpdate.setActive(1);
            userMapper.active(userUpdate);
            return true;
        }
    }
}
