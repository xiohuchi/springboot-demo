package com.demo.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author yangbin
 * @date 2020年12月30日
 */
@RestController
public class TestController {
    @Resource
    private RedissonClient redissonClient;

    @RequestMapping("/test")
    public String test(@RequestParam String name) throws InterruptedException {
        System.out.println(LocalDateTime.now() + "接收请求：" + name);
        RLock lock = redissonClient.getLock("test");
        lock.lock();
        System.out.println(LocalDateTime.now() + "处理请求：" + name);
        Thread.sleep(10000);
        lock.unlock();
        System.out.println(LocalDateTime.now() + "返回请求：" + name);
        return "Hello World";
    }
}
