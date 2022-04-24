package com.rb.alwaysontheroad.chatservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "com.rb.alwaysontheroad.chatservice")
public class ApplicationProps {
    @NotNull
    private ApplicationProps.Redis redis;
    @NotNull
    private ApplicationProps.StompBroker stompBroker;

    @Valid
    @Getter
    @Setter
    public static class Redis {
        @NotBlank
        private String host;
        @NotNull
        @Positive
        private Integer port;
    }

    @Valid
    @Getter
    @Setter
    public static class StompBroker {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String clientUsername;
        @NotBlank
        private String clientPassword;
        @NotBlank
        private String vHost;
        @NotBlank
        private String host;
        @NotNull
        @Positive
        private Integer port;
    }
}
