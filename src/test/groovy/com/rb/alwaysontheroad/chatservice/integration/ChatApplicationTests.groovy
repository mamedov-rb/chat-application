package com.rb.alwaysontheroad.chatservice.integration

import com.rb.alwaysontheroad.chatservice.config.TestContextConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class ChatApplicationTests extends TestContextConfigurer {
    @Autowired
    private ApplicationContext context

    def "Context loads"() {
        expect:
        context != null
    }
}
