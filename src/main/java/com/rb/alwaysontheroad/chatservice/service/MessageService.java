package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.dto.MessageDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Message;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import com.rb.alwaysontheroad.chatservice.repository.MessageRepository;
import com.rb.alwaysontheroad.chatservice.shared.converter.MessageDto2MessageConverter;
import com.rb.alwaysontheroad.chatservice.shared.utils.UserContextUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ParticipantService participantService;
    private final MessageDto2MessageConverter messageConverter;

    public MessageDto postNewOne(@Nullable Jwt jwt, @NotNull MessageDto messageDto) {
        @NotNull UUID currentUserId = UserContextUtil.getCurrentUserId(jwt);
        @NotNull Participant sender = participantService.findByUserId(currentUserId);
        @NotNull Message message = messageConverter.convert(messageDto, sender.getId(), Instant.now());

        return messageConverter.convert(messageRepository.save(message));
    }
}
