package com.paperless.services.impl;

import com.paperless.configuration.RabbitMQConfig;
import com.paperless.services.impl.OcrService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @Autowired
    private OcrService ocrService;

    @RabbitListener(queues = RabbitMQConfig.OCR_DOCUMENT_IN_QUEUE_NAME)
    public void receiveMessage(String minioPath) {
        ocrService.processDocument(minioPath);
    }
}
