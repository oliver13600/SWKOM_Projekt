package org.paperless.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "";

    public static final String ECHO_IN_QUEUE_NAME = "Echo_In";
    public static final String ECHO_OUT_QUEUE_NAME = "Echo_Out";

    public static final String ECHO_MESSAGE_COUNT_PROPERTY_NAME = "MessageCount";

    @Bean
    public Queue echoInQueue() {
        return new Queue(ECHO_IN_QUEUE_NAME, false);
    }

    @Bean
    public Queue echoOutQueue() { return new Queue(ECHO_OUT_QUEUE_NAME, false); }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(ECHO_IN_QUEUE_NAME);
        return rabbitTemplate;
    }
}
