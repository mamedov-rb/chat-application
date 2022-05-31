package com.rb.alwaysontheroad.chatservice.shared.converter;

import com.rb.alwaysontheroad.chatservice.model.dto.MessageDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper
public interface MessageDto2MessageConverter {

    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "createdAt", source = "createdAt")
    Message convert(MessageDto messageDto, UUID senderId, Instant createdAt);

    MessageDto convert(Message message);
}
