package com.rb.alwaysontheroad.chatservice.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "participant")
@Entity
@Accessors(chain = true)
public class Participant {

    @Id
    @GeneratedValue(generator = "HibernateUUID")
    @GenericGenerator(name = "HibernateUUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private UUID userId;

    @OneToMany(mappedBy = "participant")
    private List<ChatJoin> chatJoins;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Transient
    private static final long serialVersionUID = 1567053491060394675L;
}
