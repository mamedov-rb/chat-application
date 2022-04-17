package com.rb.alwaysontheroad.chatservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.controller.routes.Routes;
import com.rb.alwaysontheroad.chatservice.model.dto.ChatJoinDto;
import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.service.ChatJoinService;
import com.rb.alwaysontheroad.chatservice.shared.converter.ChatJoin2ChatJoinDtoConverter;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatJoinController {
    private final ChatJoinService chatJoinService;
    private final ChatJoin2ChatJoinDtoConverter chatJoinDtoConverter;

    @JsonView({View.New.class})
    @PostMapping(value = Routes.CHAT_JOIN_ROOT_URL)
    public ResponseEntity<ChatJoinDto> joinUserToChat(@Parameter(hidden = true) @AuthenticationPrincipal Jwt authentication,
                                                      @RequestBody @Validated({Validation.Create.class}) ChatJoinDto chatJoinDto) {

        @NotNull ChatJoin chatJoin = chatJoinService.joinParticipantToChat(authentication, chatJoinDto);
        return ResponseEntity.ok(chatJoinDtoConverter.convert(chatJoin));
    }
}
