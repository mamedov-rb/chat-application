package com.rb.alwaysontheroad.empty.repository;

import com.rb.alwaysontheroad.empty.model.data.ChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom, UUID> {
    Optional<ChatRoom> findByName(String name);
}
