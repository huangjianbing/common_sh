package com.dba.enums;

import lombok.Getter;

/**
 * Author:huangjb
 * Date:2018/11/4
 * Description:
 */
@Getter
public enum AppConstants {
    HTTP_PRODOCOL("http","传输协议"),
    RESHOST("192.168.183.128","fastdfs安装IP"),
    PORT("80","对应端口")
    ;

    private String arg;
    private String message;

    AppConstants(String arg, String message) {
        this.arg = arg;
        this.message = message;
    }
}
