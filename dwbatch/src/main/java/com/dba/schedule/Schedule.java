package com.dba.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author:huangjb
 * Date:2018/11/14
 * Description:
 */
@Component
@Slf4j
public class Schedule {
    //@Scheduled(cron="0 0/2 * * * ?")
    public void testSchedule(){
        Date date = new Date();
        log.info("schedule 已经启动"+date);
    }
}
