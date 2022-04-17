package com.rb.alwaysontheroad.chatservice.shared.converter;

import com.rb.alwaysontheroad.chatservice.model.dto.ChatDto;
import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatDto2ChatConverter {

    Chat convert(ChatDto chatDto);

    ChatDto convert(Chat chat);

    List<ChatDto> convert(List<Chat> chatList);
}
