package com.rb.alwaysontheroad.chatservice.repository;

import com.rb.alwaysontheroad.chatservice.model.entity.ChatJoin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatJoinRepository extends PagingAndSortingRepository<ChatJoin, UUID> {
}
