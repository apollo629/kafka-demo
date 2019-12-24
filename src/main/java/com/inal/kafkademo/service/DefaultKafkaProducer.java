package com.inal.kafkademo.service;

import com.inal.kafkademo.request.AuthRequest;
import com.inal.kafkademo.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultKafkaProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public DefaultKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Request request, String topicName){
        logger.info("Message is publishing to topic: {} message: {}", topicName, request.toString());
        kafkaTemplate.send(topicName, UUID.randomUUID().toString(), request);
    }
}
