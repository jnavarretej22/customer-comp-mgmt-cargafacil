package com.cargafacil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Prefijos para el cliente
        config.enableSimpleBroker("/topic"); // Envíos del servidor al cliente
        config.setApplicationDestinationPrefixes("/app"); // Envíos del cliente al servidor
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/tracking-websocket") // Endpoint del WebSocket
                .setAllowedOrigins("*") // Permitir conexiones desde cualquier origen
                .withSockJS(); // Soporte para clientes que no soportan WebSocket
    }
}
