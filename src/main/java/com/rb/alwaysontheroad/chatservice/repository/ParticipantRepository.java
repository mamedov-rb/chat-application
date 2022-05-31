package com.rb.alwaysontheroad.chatservice.repository;

import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends PagingAndSortingRepository<Participant, UUID> {

    List<Participant> findAllByChatJoinsIn(@NotNull List<ChatJoin> chatJoins);

    List<Participant> findAllByIdIn(@NotNull List<UUID> chatJoins);

//    Optional<ParticipantIdProjection> findByUserId(@NotNull UUID uuid);

    Optional<Participant> findByUserId(@NotNull UUID uuid);
}
