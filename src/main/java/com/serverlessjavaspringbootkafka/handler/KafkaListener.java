package com.serverlessjavaspringbootkafka.handler;

import com.amazonaws.services.lambda.runtime.events.KafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class KafkaListener {

    @Bean
    public Consumer<Message<KafkaEvent>> pollMessageFromKafka() {
        return kafkaEventMessage -> log.info(String.format("Message received: %s", kafkaEventMessage));
    }
}
