package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import com.rb.alwaysontheroad.chatservice.repository.ChatRepository;
import com.rb.alwaysontheroad.chatservice.shared.exception.ChatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @NotNull
    @Cacheable(value = "chat", key = "#id")
    @Transactional(readOnly = true)
    public Chat findById(@NotNull UUID id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ChatException(String.format("Chat with id: '%s' - Not found.", id)));
    }

    @NotNull
    @Cacheable(value = "chats-by-name", key = "#name")
    @Transactional(readOnly = true)
    public Page<Chat> findAllByNameLike(String name, Pageable pageable) {
        return chatRepository.findByNameLike(StringUtils.wrap(name, "%"), pageable);
    }

    @NotNull
    @CacheEvict(value = "chats-by-name", allEntries=true)
    @Transactional
    public Chat createNewOne(@NotNull Chat chat) {
        return chatRepository.save(chat);
    }
}
