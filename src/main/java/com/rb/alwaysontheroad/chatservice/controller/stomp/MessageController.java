package com.rb.alwaysontheroad.chatservice.controller.stomp;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.model.dto.MessageDto;
import com.rb.alwaysontheroad.chatservice.service.MessageService;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @JsonView({View.New.class})
    @MessageMapping("/chat/{id}")
    @SendTo("/topic/chat.{id}")
    public MessageDto postNewOne(@Nullable @Parameter(hidden = true) @AuthenticationPrincipal Jwt jwt,
                                 @DestinationVariable String id,
                                 @RequestBody @Validated({Validation.Create.class}) MessageDto messageDto) {

        log.debug("Received message: '{}' for chat: '{}'", messageDto, id);
        return messageService.postNewOne(jwt, messageDto);
    }
}
