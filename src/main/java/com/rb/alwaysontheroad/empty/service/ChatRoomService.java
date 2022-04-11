package com.rb.alwaysontheroad.empty.service;

import com.rb.alwaysontheroad.empty.model.data.ChatRoom;
import com.rb.alwaysontheroad.empty.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Cacheable(value = "chat-room")
    @Transactional(readOnly = true)
    public ChatRoom findByName(String name) {
        return chatRoomRepository.findByName(name)
                .orElseThrow();
    }
}
