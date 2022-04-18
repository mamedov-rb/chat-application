package com.rb.alwaysontheroad.chatservice.integration

import com.rb.alwaysontheroad.chatservice.config.IntegrationTestContextConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class ChatApplicationTests extends IntegrationTestContextConfigurer {
    @Autowired
    private ApplicationContext context

    def "Context loads"() {
        expect:
        context != null
    }
}
