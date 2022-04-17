package com.rb.alwaysontheroad.chatservice.repository;

import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends PagingAndSortingRepository<Participant, UUID> {

    List<Participant> findAllByChatJoinsIn(List<ChatJoin> chatJoins);

    Optional<Participant> findByUserId(UUID uuid);
}
