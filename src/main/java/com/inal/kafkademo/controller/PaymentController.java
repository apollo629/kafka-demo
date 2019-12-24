package com.inal.kafkademo.controller;

import com.inal.kafkademo.request.AuthRequest;
import com.inal.kafkademo.request.CancelRequest;
import com.inal.kafkademo.response.Response;
import com.inal.kafkademo.service.DefaultKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.inal.kafkademo.config.Constants.AUTH_KAFKA_TOPIC_NAME;
import static com.inal.kafkademo.config.Constants.CANCEL_KAFKA_TOPIC_NAME;

@RestController
public class PaymentController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final DefaultKafkaProducer defaultKafkaProducer;

    public PaymentController(DefaultKafkaProducer defaultKafkaProducer) {
        this.defaultKafkaProducer = defaultKafkaProducer;
    }

    @PostMapping("/auth")
    public Response auth(@RequestBody AuthRequest authRequest){
        Response response = new Response();
        try {
            defaultKafkaProducer.publish(authRequest, AUTH_KAFKA_TOPIC_NAME);
            response.setStatus("Success");
        } catch (Exception e) {
            logger.error("Exception occurred while publishing message to kafka.", e);
            response.setStatus("Failure");
        }
        return response;
    }

    @PostMapping("/cancel")
    public Response cancel(@RequestBody CancelRequest cancelRequest){
        Response response = new Response();
        try {
            defaultKafkaProducer.publish(cancelRequest, CANCEL_KAFKA_TOPIC_NAME);
            response.setStatus("Success");
        } catch (Exception e) {
            logger.error("Exception occurred while publishing message to kafka.", e);
            response.setStatus("Failure");
        }
        return response;
    }

}
