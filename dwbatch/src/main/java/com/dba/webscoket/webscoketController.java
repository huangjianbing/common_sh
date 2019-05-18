package com.dba.webscoket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:huangjb
 * Date:2018/11/13
 * Description:
 */
@Controller
@Slf4j
public class webscoketController {

    @RequestMapping("/webscokethtml")
    public String webScoketHtml(){
        log.info("webscoket");
        return "webscoket";
    }
}
