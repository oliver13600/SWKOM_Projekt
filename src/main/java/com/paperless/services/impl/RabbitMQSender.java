package com.paperless.services.impl;

import com.paperless.configuration.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToOcrDocumentInQueue(String message) {
        log.info("Sending message to OCR Document In Queue: " + message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.OCR_DOCUMENT_IN_QUEUE_NAME, message);
    }
}
