package com.rb.alwaysontheroad.chatservice.config;

import com.rb.alwaysontheroad.chatservice.shared.event.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableScheduling
@EnableRedisHttpSession
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {
    private final ApplicationProps applicationProps;

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(Routes.WS_PATH)
                .setAllowedOriginPatterns("*");
        registry.addEndpoint(Routes.WS_PATH)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        ApplicationProps.StompBroker stompBroker = applicationProps.getStompBroker();

        registry.enableStompBrokerRelay(Routes.TOPIC_DESTINATION_ROOT, Routes.QUEUE_DESTINATION_ROOT)
                .setUserDestinationBroadcast(Routes.UNRESOLVED_USERS_TOPIC)
                .setUserRegistryBroadcast(Routes.REGISTRY_BROADCAST_TOPIC)

                .setSystemLogin(stompBroker.getUsername())
                .setSystemPasscode(stompBroker.getPassword())
                .setClientLogin(stompBroker.getClientUsername())
                .setClientPasscode(stompBroker.getClientPassword())

                .setVirtualHost(stompBroker.getVHost())
                .setRelayHost(stompBroker.getHost())
                .setRelayPort(stompBroker.getPort());

        registry.setApplicationDestinationPrefixes(Routes.APP_DESTINATION_PREFIX);
    }
}
