package com.rb.alwaysontheroad.chatservice.model.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
public class MessageEvent {

    private UUID senderId;
    private UUID chatId;
    private Instant createdAt;
    private String payload;
}
