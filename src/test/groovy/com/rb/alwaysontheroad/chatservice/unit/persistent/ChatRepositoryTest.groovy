package com.rb.alwaysontheroad.chatservice.unit.persistent

import com.rb.alwaysontheroad.chatservice.config.PersistentTestContextConfigurer
import com.rb.alwaysontheroad.chatservice.model.entity.Chat
import com.rb.alwaysontheroad.chatservice.model.enums.ChatType
import com.rb.alwaysontheroad.chatservice.repository.ChatRepository
import org.springframework.beans.factory.annotation.Autowired

import static org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils.randomAlphabetic

class ChatRepositoryTest extends PersistentTestContextConfigurer {
    @Autowired
    private ChatRepository chatRepository

    def "saving new chat is success"() {
        given:
        def chat = new Chat()
                .setName(randomAlphabetic(20))
                .setDescription(randomAlphabetic(20))
                .setType(ChatType.DIALOG)

        when:
        def savedChat = chatRepository.save(chat)

        then:
        savedChat.id != null
        savedChat.name == chat.name
        savedChat.description == chat.description
        savedChat.type == chat.type
    }
}
