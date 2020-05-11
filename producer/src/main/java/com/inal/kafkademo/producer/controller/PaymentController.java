package com.inal.kafkademo.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inal.kafkademo.commons.Constants;
import com.inal.kafkademo.commons.request.AuthRequest;
import com.inal.kafkademo.commons.request.CancelRequest;
import com.inal.kafkademo.commons.response.Response;
import com.inal.kafkademo.producer.service.DefaultKafkaProducer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PaymentController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final DefaultKafkaProducer defaultKafkaProducer;

    public PaymentController(DefaultKafkaProducer defaultKafkaProducer) {
        this.defaultKafkaProducer = defaultKafkaProducer;
    }

    @PostMapping("/auth")
    public Response auth(@RequestBody AuthRequest authRequest) {
        Response response = new Response();
        try {
            defaultKafkaProducer.publish(authRequest, Constants.AUTH_KAFKA_TOPIC_NAME);
            response.setStatus("Success");
        } catch (Exception e) {
            logger.error("Exception occurred while publishing message to kafka.", e);
            response.setStatus("Failure");
        }
        return response;
    }

    @PostMapping("/cancel")
    public Response cancel(@RequestBody CancelRequest cancelRequest) {
        Response response = new Response();
        try {
            defaultKafkaProducer.publish(cancelRequest, Constants.CANCEL_KAFKA_TOPIC_NAME);
            response.setStatus("Success");
        } catch (Exception e) {
            logger.error("Exception occurred while publishing message to kafka.", e);
            response.setStatus("Failure");
        }
        return response;
    }

    @PostMapping("/basic/test")
    @ResponseStatus(HttpStatus.OK)
    public Response calculateHash(@RequestBody MyRequest request, HttpServletRequest httpServletRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        String json = "";
        try {
            json = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(json);
        logger.info("String used to calculate HASH:{}", request.getMerchantId() + "1" + json);
        logger.info("Hash from request header: {} ", httpServletRequest.getHeader("Authorization"));
        return new Response();
    }
}
