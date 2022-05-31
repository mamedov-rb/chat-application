package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.dto.ChatJoinDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import com.rb.alwaysontheroad.chatservice.repository.ChatJoinRepository;
import com.rb.alwaysontheroad.chatservice.shared.converter.ChatJoin2ChatJoinDtoConverter;
import com.rb.alwaysontheroad.chatservice.shared.utils.UserContextUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    private final ChatJoin2ChatJoinDtoConverter chatJoinDtoConverter;

    @NotNull
    @Transactional
    public ChatJoinDto joinParticipantToChat(@Nullable Jwt jwt, @NotNull ChatJoinDto chatJoinDto) {
        @NotNull UUID currentUserId = UserContextUtil.getCurrentUserId(jwt);
        @NotNull Participant participant = participantService.findByUserIdOrCreate(currentUserId);
        @NotNull Chat chat = chatService.findById(chatJoinDto.getChatId());

        @NotNull ChatJoin chatJoin = new ChatJoin()
                .setParticipant(participant)
                .setChat(chat);

        @NotNull ChatJoin savedChatJoin = chatJoinRepository.save(chatJoin);
        return chatJoinDtoConverter.convert(savedChatJoin);
    }
}
