package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import com.rb.alwaysontheroad.chatservice.repository.ParticipantRepository;
import com.rb.alwaysontheroad.chatservice.shared.exception.ParticipantException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @NotNull
    @Cacheable(value = "participants", key = "#chatId")
    public List<Participant> findByChatId(@NotNull UUID chatId) {
        return participantRepository.findAllByChatJoinsIn(List.of(new ChatJoin().setId(chatId)));
    }

    @NotNull
    @Cacheable(value = "participants", key = "#ids")
    public List<Participant> findByIdsIn(@NotNull List<UUID> ids) {
        return participantRepository.findAllByIdIn(ids);
    }

    @NotNull
    public Participant findById(UUID id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ParticipantException(String.format("Participant with id: '%s' not found'", id)));
    }

    @NotNull
    public Participant findByUserId(@NotNull UUID userId) {
        return participantRepository.findByUserId(userId)
                .orElseThrow(() -> new ParticipantException(String.format("Participant with userId: '%s' not found'", userId)));
    }

    @NotNull
    @Transactional
    public Participant findByUserIdOrCreate(@NotNull UUID id) {
        return participantRepository.findByUserId(id)
                .orElseGet(() -> participantRepository.save(new Participant().setUserId(id)));
    }
}
