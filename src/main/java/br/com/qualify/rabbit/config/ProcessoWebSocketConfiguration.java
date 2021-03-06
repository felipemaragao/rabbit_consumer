package br.com.qualify.rabbit.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
public class ProcessoWebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


        public static final String BROKER = "processosCreated";

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/socket")
                    .setAllowedOrigins("*")
                    .withSockJS();
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            registry.enableSimpleBroker(BROKER);
        }
}

