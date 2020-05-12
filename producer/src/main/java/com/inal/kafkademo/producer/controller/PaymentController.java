package com.inal.kafkademo.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inal.kafkademo.commons.Constants;
import com.inal.kafkademo.commons.request.AuthRequest;
import com.inal.kafkademo.commons.request.CancelRequest;
import com.inal.kafkademo.commons.response.Response;
import com.inal.kafkademo.producer.service.DefaultKafkaProducer;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
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
        String stringToBeHash = new StringBuilder(request.getMerchantId().toString())
                .append("1")
                .append(request.getIyziEventType())
                .append(request.getPaymentId())
                .append(request.getPaymentTxId())
                .append(request.getRefundConversationId())
                .append(request.getStatus())
                .toString();

        logger.info("String used to calculate HASH: {}", stringToBeHash);
        String hashFromHeader = httpServletRequest.getHeader("Authorization");
        logger.info("Hash from request header: {}", hashFromHeader);
        String generatedHash = Base64.encodeBase64String(DigestUtils.sha1(stringToBeHash));
        logger.info("Generated Hash: {}", generatedHash);
        logger.info("Hash comparision: {}", StringUtils.equals(hashFromHeader, generatedHash));
        return new Response();
    }
}
