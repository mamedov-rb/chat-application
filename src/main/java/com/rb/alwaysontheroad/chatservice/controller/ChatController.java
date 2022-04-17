package com.rb.alwaysontheroad.chatservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.controller.routes.Routes;
import com.rb.alwaysontheroad.chatservice.model.dto.ChatDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import com.rb.alwaysontheroad.chatservice.service.ChatService;
import com.rb.alwaysontheroad.chatservice.shared.converter.ChatDto2ChatConverter;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ChatDto2ChatConverter chatConverter;

    @JsonView({View.New.class})
    @PostMapping(
            value = Routes.CHAT_ROOT_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ChatDto> createNewOne(@RequestBody @Validated({Validation.Create.class}) ChatDto chatDto) {
        @NotNull Chat savedChat = chatService.createNewOne(chatConverter.convert(chatDto));
        return ResponseEntity.ok(chatConverter.convert(savedChat));
    }

    @GetMapping(value = Routes.ALL_CHAT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ChatDto>> findAllByNameLike(@RequestParam String name,
                                                           @RequestParam(required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(required = false, defaultValue = "10") Integer perPage) {

        @NotNull Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, Chat.Fields.createdAt));

        @NotNull Page<Chat> chatPage = chatService.findAllByNameLike(name, pageable);

        @NotNull List<ChatDto> chatDtos = chatPage.getContent().stream()
                .map(chatConverter::convert).collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(chatDtos, pageable, chatPage.getTotalElements()));
    }
}
