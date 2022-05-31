package com.rb.alwaysontheroad.chatservice.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Data
@Table("message")
@Accessors(chain = true)
public class Message {

    @PrimaryKeyColumn(name = "sender_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID senderId;

    @PrimaryKeyColumn(name = "chat_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private UUID chatId;

    @PrimaryKeyColumn(name = "created_at", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private Instant createdAt;

    private String payload;
}
