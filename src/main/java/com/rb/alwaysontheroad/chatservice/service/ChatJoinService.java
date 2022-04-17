package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.dto.ChatJoinDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import com.rb.alwaysontheroad.chatservice.repository.ChatJoinRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatJoinService {
    private final ChatService chatService;
    private final ParticipantService participantService;
    private final ChatJoinRepository chatJoinRepository;

    @NotNull
    @Transactional
    public ChatJoin joinParticipantToChat(@NotNull Jwt authentication, @NotNull ChatJoinDto chatJoinDto) {
        @NotNull UUID currentUserId = UUID.fromString((String) authentication.getClaims().get("sub"));
        @NotNull Participant participant = participantService.findOrCreate(currentUserId);
        @NotNull Chat chat = chatService.findById(chatJoinDto.getChatId());
        return chatJoinRepository.save(new ChatJoin()
                .setParticipant(participant)
                .setChat(chat)
        );
    }
}
