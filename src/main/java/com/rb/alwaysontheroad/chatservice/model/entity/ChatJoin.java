package com.rb.alwaysontheroad.chatservice.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.UUID;

@Data
@Table(name = "chat_join")
@Entity
@Accessors(chain = true)
public class ChatJoin {

    @Id
    @GeneratedValue(generator = "HibernateUUID")
    @GenericGenerator(name = "HibernateUUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @CreationTimestamp
    private Instant createdAt;

    @Transient
    private static final long serialVersionUID = 2577753491060394677L;
}
