package br.com.qualify.rabbit.consumer;

import br.com.qualify.rabbit.config.ProcessoAMQPConfig;
import br.com.qualify.rabbit.config.ProcessoWebSocketConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcessoConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = ProcessoAMQPConfig.QUEUE)
    public void consumer(Message message) {
        simpMessagingTemplate.convertAndSend(ProcessoWebSocketConfiguration.BROKER,
                new String(message.getBody()));
    }
}
