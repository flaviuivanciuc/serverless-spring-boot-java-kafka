package com.serverlessjavaspringbootkafka.handler;

import com.amazonaws.services.lambda.runtime.events.KafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.function.Consumer;

@Component
@Slf4j
public class KafkaEventHandler {

    @Bean
    public Consumer<Message<KafkaEvent>> pollMessageFromKafka() {
        return kafkaEventMessage -> {
            log.info(String.format("Message received: %s", kafkaEventMessage));
            kafkaEventMessage.getPayload().getRecords().forEach((s, kafkaEventRecords) -> {
                log.info(String.format("kafkaEventRecords: %s", kafkaEventRecords));

                kafkaEventRecords.forEach(message -> {
                    byte[] decodedBytes = Base64.getDecoder().decode(message.getValue());
                    String decodedMessage = new String(decodedBytes);
                    log.info(String.format("Message: %s", decodedMessage));
                });
            });
        };
    }
}
