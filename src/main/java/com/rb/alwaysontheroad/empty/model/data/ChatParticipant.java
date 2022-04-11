package com.rb.alwaysontheroad.empty.model.data;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Data
@Table(name = "chat_participant")
@Entity
public class ChatParticipant {

    @Id
    @GeneratedValue(generator = "HibernateUUID")
    @GenericGenerator(name = "HibernateUUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private UUID userId;

    @ManyToOne(optional = false)
    private ChatRoom chatRoom;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
