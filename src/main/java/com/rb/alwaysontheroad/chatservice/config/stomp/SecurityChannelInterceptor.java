package com.rb.alwaysontheroad.chatservice.config.stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityChannelInterceptor implements ChannelInterceptor {
//    private final JwtDecoder jwtDecoder;

    @Override
    public Message<?> preSend(@NotNull Message<?> message, @NotNull MessageChannel channel) {
        @NotNull StompHeaderAccessor accessor = Optional
                .ofNullable(MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class))
                .orElseThrow();

        @Nullable StompCommand command = accessor.getCommand();

        if (StompCommand.CONNECT.equals(command)) {
//            String accessToken = StringUtils.replace(accessor.getFirstNativeHeader("Authorization"), "Bearer ", "");
            Jwt jwt = ((JwtAuthenticationToken) accessor.getHeader("simpUser")).getToken();
//            Jwt jwt = jwtDecoder.decode(accessToken);
            accessor.setUser(new JwtAuthenticationConverter().convert(jwt));
        }
        return message;
    }
}
