package com.jiteshTechGuru.kafkaExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public  void publishMsg(String msg){
        kafkaTemplate.send("JiteshTechGuru-demo-topic1",msg);
    }


}
