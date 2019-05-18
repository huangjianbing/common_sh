package com.dba.controller;

import com.dba.config.FastDFSClientWrapper;
import com.dba.message.IndustrySMS;
import com.dba.pojo.User;
import com.dba.service.UserService;
import com.dba.utils.RandomCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * Author:huangjb
 * Date:2018/11/2
 * Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FastDFSClientWrapper dfsClient;

    @RequestMapping(value = "/uploadImg")
    public String upload(HttpServletRequest request,MultipartFile file) throws Exception {
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        Iterator<String> fileNames = req.getFileNames();
        file = req.getFile(fileNames.next());
        log.info("file:"+file.getOriginalFilename());
        String imgUrl = dfsClient.uploadFile(file);
        log.info("imgUrl:"+imgUrl);
        return imgUrl;
    }
    @RequestMapping("/getAllUser")
    public List<User> getAllUser(){
        log.info("User:"+userService.getAllUser());
        return userService.getAllUser();
    }
    @RequestMapping("/getUserById")
    public User gerUserById(User user){
        if(user!=null){
            return userService.getUserById(user.getId());
        }
      else{
            log.error("传入的参数异常");
            return null;
        }
    }
    @RequestMapping("/updateUser")
    public User updatUser(User user){
        log.info("修改用户信息："+user.toString());
        return userService.updateUser(user) ;
    }
    @RequestMapping("/getUserByPhone")
    public User getUserByPhone(User user){
        return userService.getUserByphone(user.getPhone());
    }
    @RequestMapping("/registerUser")
    public Boolean registerUser( HttpSession session, User user,String code){
        int cod1 = (int) session.getAttribute(user.getPhone());
        Integer cod= Integer.valueOf(code);
        if(cod1!=cod){
            return false;
        }
        Boolean flag=userService.registerUser(user);
        return flag;
    }
    @RequestMapping("/activeUser")
    public String  activeUser (String key){
        Boolean flag=userService.userService(key);
        if(flag){
            return "激活成功";
        }
        else{
            return  "确认链接是否有效";
        }
    }
    @RequestMapping("/sendMessage")
    public void sendMessage(HttpSession session,String phone){
        IndustrySMS ids =new IndustrySMS();
        int randNum = RandomCode.getRandNum(1, 999999);
        session.setAttribute(phone,randNum);
        session.setMaxInactiveInterval(30 * 60);
        ids.execute(phone,randNum );
    }
    @RequestMapping("/login")
    public Boolean login(User user){
        Boolean flag=userService.login( user);
        return flag;
    }
}
