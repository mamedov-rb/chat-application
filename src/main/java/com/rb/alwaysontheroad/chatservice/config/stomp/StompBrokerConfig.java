package com.rb.alwaysontheroad.chatservice.config.stomp;

import com.rb.alwaysontheroad.chatservice.config.ApplicationProps;
import com.rb.alwaysontheroad.chatservice.model.event.BrokerRoutes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Configuration
@EnableScheduling
@EnableRedisHttpSession
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompBrokerConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {
    private final ApplicationProps applicationProps;
    private final SecurityChannelInterceptor channelInterceptor;

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(BrokerRoutes.WS_PATH)
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        ApplicationProps.StompBroker stompBroker = applicationProps.getStompBroker();

        registry
                .enableStompBrokerRelay(BrokerRoutes.TOPIC_DESTINATION_ROOT, BrokerRoutes.QUEUE_DESTINATION_ROOT)
                .setUserDestinationBroadcast(BrokerRoutes.UNRESOLVED_USERS_TOPIC)
                .setUserRegistryBroadcast(BrokerRoutes.REGISTRY_BROADCAST_TOPIC)

                .setSystemLogin(stompBroker.getUsername())
                .setSystemPasscode(stompBroker.getPassword())

                .setClientLogin(stompBroker.getClientUsername())
                .setClientPasscode(stompBroker.getClientPassword())

                .setVirtualHost(stompBroker.getVHost())
                .setRelayHost(stompBroker.getHost())
                .setRelayPort(stompBroker.getPort());

        registry
                .setApplicationDestinationPrefixes(BrokerRoutes.APP_DESTINATION_PREFIX)
                .setPathMatcher(new AntPathMatcher("/"));
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor);
    }
}
