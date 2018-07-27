package com.liaohua.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * Created by liaohua on 2018/7/26.
 */
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();
        //让线程等待几秒
        int sleeptime = new Random().nextInt(3000);
        logger.info("sleeptime"+sleeptime);
        Thread.sleep(sleeptime);
        logger.info("/hello,host: "+instance.getHost()+", service_id: "+instance.getServiceId());
        return "Hello World";
    }
}
