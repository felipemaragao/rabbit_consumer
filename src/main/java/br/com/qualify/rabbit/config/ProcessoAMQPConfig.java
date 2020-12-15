package br.com.qualify.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessoAMQPConfig {
    public static final String QUEUE = "processos-created";
    public static final String EXCHANGE_NAME = "Processos";
    public static final String ROUTING_KEY = "";

    final static String retryQueue = "retry-queue";
    final static String workExchange = "work-exchange";

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE)
                .build();
    }

    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }

    @Bean
    @Qualifier("retry")
    Queue retryQueue() {
        return QueueBuilder
                .durable(retryQueue)
                .withArgument("x-dead-letter-exchange", workExchange)
                .withArgument("x-message-ttl", 30000)
                .build();
    }


}
