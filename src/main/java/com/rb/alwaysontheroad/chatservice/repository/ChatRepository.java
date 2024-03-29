package com.rb.alwaysontheroad.chatservice.repository;

import com.rb.alwaysontheroad.chatservice.model.entity.Chat;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends PagingAndSortingRepository<Chat, UUID> {

    Optional<Chat> findByName(@NotNull String name);

    Page<Chat> findByNameLike(@NotNull String name, @NotNull Pageable pageable);
}
