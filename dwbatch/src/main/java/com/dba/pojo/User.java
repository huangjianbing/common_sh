package com.dba.pojo;

import lombok.Data;

/**
 * Author:huangjb
 * Date:2018/11/2
 * Description:
 */
@Data
public class User {
    private  String id;
    private String userName;
    private String password;
    private String img;
    private String sex;
    private String phone;
    private String email;
    private String address;
    private Integer active;
    private String remark;
    
}
