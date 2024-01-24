package com.example.leetcode_clone.config.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${debug}")
    private boolean debug;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/submittion_run/");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        if (debug) {
            registry.addEndpoint("/websocket").setAllowedOriginPatterns("*").withSockJS();
            registry.addEndpoint("/websocket").setAllowedOriginPatterns("*");
        } else {
            registry.addEndpoint("/websocket").withSockJS();
            registry.addEndpoint("/websocket");
        }
    }
}
