package com.rb.alwaysontheroad.chatservice.model.entity;

import com.rb.alwaysontheroad.chatservice.model.enums.ChatType;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "chat")
@Entity
@Accessors(chain = true)
@FieldNameConstants
public class Chat implements Serializable {

    @Id
    @GeneratedValue(generator = "HibernateUUID")
    @GenericGenerator(name = "HibernateUUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private ChatType type;

    @OneToMany(mappedBy = "chat")
    private List<ChatJoin> chatJoins;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Transient
    private static final long serialVersionUID = 2567653491060394677L;
}
