package com.rb.alwaysontheroad.chatservice.service;

import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import com.rb.alwaysontheroad.chatservice.model.entity.Participant;
import com.rb.alwaysontheroad.chatservice.repository.ParticipantRepository;
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
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @NotNull
    @Cacheable(value = "participants", key = "#id")
    @Transactional(readOnly = true)
    public List<Participant> findByChatRoom(@NotNull UUID id) {
        return participantRepository.findAllByChatJoinsIn(List.of(new ChatJoin().setId(id)));
    }

    @NotNull
    @Transactional
    public Participant findByUserIdOrCreate(@NotNull UUID id) {
        return participantRepository.findByUserId(id)
                .orElseGet(() -> participantRepository.save(new Participant().setUserId(id)));
    }
}
