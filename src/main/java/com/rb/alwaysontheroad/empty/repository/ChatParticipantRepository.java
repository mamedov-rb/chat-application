package com.rb.alwaysontheroad.empty.repository;

import com.rb.alwaysontheroad.empty.model.data.ChatParticipant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface ChatParticipantRepository extends CrudRepository<ChatParticipant, UUID> {
    Stream<ChatParticipant> findAllByChatRoomId(UUID id);
}
