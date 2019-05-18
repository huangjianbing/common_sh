package com.dba.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:huangjb
 * Date:2018/11/14
 * Description:
 */
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/redis")
    public String testRedis(){
        stringRedisTemplate.opsForValue().set("huang","黄建斌");
        String huang = stringRedisTemplate.opsForValue().get("huang");
        return huang;
    }
}
