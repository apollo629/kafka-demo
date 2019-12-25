package com.inal.kafkademo.consumer;

import com.inal.kafkademo.request.AuthRequest;
import com.inal.kafkademo.service.CutoffMerchantTxService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

import static com.inal.kafkademo.config.Constants.AUTH_KAFKA_TOPIC_NAME;

@Service
public class AuthConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(AuthConsumerService.class);

    private final CutoffMerchantTxService cutoffMerchantTxService;

    public AuthConsumerService(CutoffMerchantTxService cutoffMerchantTxService) {
        this.cutoffMerchantTxService = cutoffMerchantTxService;
    }

    @KafkaListener(topics = AUTH_KAFKA_TOPIC_NAME, clientIdPrefix = "first-auth", containerFactory = "kafkaListenerContainerFactory")
    public void firstAuthConsumer(ConsumerRecord<String, AuthRequest> cr,
                               @Payload AuthRequest payload) {
        logger.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
                typeIdHeader(cr.headers()), payload, cr.toString());
        cutoffMerchantTxService.create(payload);
    }

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
                .filter(header -> header.key().equals("__TypeId__"))
                .findFirst().map(header -> new String(header.value())).orElse("N/A");
    }

}
