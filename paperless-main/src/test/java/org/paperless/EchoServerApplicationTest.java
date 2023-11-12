package org.paperless;


import org.paperless.configuration.RabbitMQConfig;
import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EchoServerApplicationTests {

    public static final String TEST_MESSAGE = "ABC";
    public static final String TEST_VALUE = "12345";

    @Test
    void contextLoads() {
    }

    @Test
    void echoService_shouldReceiveResponse() throws IOException, InterruptedException {
        RabbitMQConfig config = new RabbitMQConfig();
        final AtomicBoolean answerReceived = new AtomicBoolean(false);

        // init receiver (=queue message consumer)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received: " + message);

            assertEquals( "Echo " + TEST_MESSAGE, message );

            synchronized (answerReceived) {
                answerReceived.set(true);
                answerReceived.notifyAll();
            }
        };
        config.connectionFactory()
                .createConnection()
                .createChannel(false)
                .basicConsume(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        // do transmit
        //echoService.processMessage( mapper.writeValueAsString(doc) ); // this would bypass the ECHO_IN_QUEUE
        RabbitTemplate rabbitTemplate = config.rabbitTemplate();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ECHO_IN_QUEUE_NAME, TEST_MESSAGE, message1 -> {
            message1.getMessageProperties().getHeaders().put(RabbitMQConfig.ECHO_MESSAGE_COUNT_PROPERTY_NAME, TEST_VALUE);
            return message1;
        });

        synchronized ( answerReceived ) {
            answerReceived.wait(5000);
        }

        assertTrue( answerReceived.get() );
    }


}
