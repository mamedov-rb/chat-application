package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.dto.ChatDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import com.rb.alwaysontheroad.chatservice.repository.ChatRepository;
import com.rb.alwaysontheroad.chatservice.shared.converter.ChatDto2ChatConverter;
import com.rb.alwaysontheroad.chatservice.shared.exception.ChatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatDto2ChatConverter chatConverter;

    @NotNull
    public Chat findById(@NotNull UUID id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ChatException(String.format("Chat with id: '%s' - Not found.", id)));
    }

    @NotNull
    @Cacheable(value = "chats-by-name", key = "#nameLike")
    public Page<ChatDto> findAllByNameLike(@NotNull String nameLike, @NotNull Integer page, @NotNull Integer perPage) {
        @NotNull Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, Chat.Fields.createdAt));
        @NotNull Page<Chat> chatPage = chatRepository.findByNameLike(nameLike, pageable);

        @NotNull List<ChatDto> chatDtos = chatPage.getContent().stream()
                .map(chatConverter::convert).collect(Collectors.toList());

        return new PageImpl<>(chatDtos, pageable, chatPage.getTotalElements());
    }

    @NotNull
    @CacheEvict(value = "chats-by-name", allEntries = true)
    @Transactional
    public ChatDto createNewOne(@NotNull ChatDto chatDto) {
        @NotNull Chat chat = chatConverter.convert(chatDto);
        return chatConverter.convert(chatRepository.save(chat));
    }
}
