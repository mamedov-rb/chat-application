package com.rb.alwaysontheroad.chatservice.shared.converter;

import com.rb.alwaysontheroad.chatservice.model.entity.Message;
import com.rb.alwaysontheroad.chatservice.model.event.MessageEvent;
import org.mapstruct.Mapper;

@Mapper
public interface MessageEvent2MessageConverter {

    Message convert(MessageEvent messageEvent);

    MessageEvent convert(Message messageEvent);
}
