package org.paperless.services;


import org.paperless.configuration.RabbitMQConfig;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A console application to test the HelloResponseService
 * <p>
 * Taken from <a href="https://www.rabbitmq.com/tutorials/tutorial-one-java.html">...</a>
 */
public class EchoClientApplication {
    public static void main(String[] args) throws IOException {
        RabbitMQConfig config = new RabbitMQConfig();
        final AtomicInteger messageCount = new AtomicInteger();

        // init receiver (=queue message consumer)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("\nYou received: " + message);
        };
        config.connectionFactory()
                .createConnection()
                .createChannel(false)
                .basicConsume(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        // prepare sender (=queue message producer)
        RabbitTemplate rabbitTemplate = config.rabbitTemplate();

        Scanner scanner = new Scanner(System.in);
        String message;
        do {
            System.out.print("Enter your message, empty to use default messages or q to quit: ");
            message = scanner.nextLine();
            System.out.println("You entered: " + message);

            if (!"q".equals(message)) {
                messageCount.incrementAndGet();

                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ECHO_IN_QUEUE_NAME, message, message1 -> {
                    message1.getMessageProperties().getHeaders().put(RabbitMQConfig.ECHO_MESSAGE_COUNT_PROPERTY_NAME, messageCount.get());
                    return message1;
                });
            }

        } while(!"q".equals(message));

        System.exit(0);
    }
}

