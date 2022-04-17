package com.rb.alwaysontheroad.chatservice.shared.converter;

import com.rb.alwaysontheroad.chatservice.model.dto.ChatJoinDto;
import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ChatJoin2ChatJoinDtoConverter {

    @Mapping(target = "chatId", source = "chatJoin.chat.id")
    @Mapping(target = "participantId", source = "chatJoin.participant.id")
    ChatJoinDto convert(ChatJoin chatJoin);
}
