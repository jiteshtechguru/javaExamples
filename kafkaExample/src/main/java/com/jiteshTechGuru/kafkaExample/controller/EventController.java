package com.jiteshTechGuru.kafkaExample.controller;

import com.jiteshTechGuru.kafkaExample.service.KafkaPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {


    @Autowired
    private KafkaPublisher kafkaPublisher;
    @GetMapping("/publishMsg/{msg}")
    public String sendMsg(@PathVariable String msg){
        kafkaPublisher.publishMsg(msg);
        return msg +" send successfully";
    }
}
