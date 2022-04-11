package com.rb.alwaysontheroad.empty.config;

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
    private Redis redis;

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
}
