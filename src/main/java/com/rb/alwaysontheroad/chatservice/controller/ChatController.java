package com.rb.alwaysontheroad.chatservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.controller.routes.Routes;
import com.rb.alwaysontheroad.chatservice.model.dto.ChatDto;
import com.rb.alwaysontheroad.chatservice.service.ChatService;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @JsonView({View.New.class})
    @PostMapping(
            value = Routes.CHAT_ROOT_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ChatDto> createNewOne(@RequestBody @Validated({Validation.Create.class}) ChatDto chatDto) {
        return ResponseEntity.ok(chatService.createNewOne(chatDto));
    }

    @GetMapping(value = Routes.ALL_CHAT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ChatDto>> findAllByNameLike(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(required = false, defaultValue = "10") Integer perPage) {

        @NotNull String nameLike = Optional.ofNullable(name)
                .map(s -> StringUtils.wrap(s, "%"))
                .orElse("%%");
        return ResponseEntity.ok(chatService.findAllByNameLike(nameLike, page, perPage));
    }
}
