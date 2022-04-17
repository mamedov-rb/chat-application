package com.rb.alwaysontheroad.chatservice.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(["test"])
@Testcontainers
abstract class TestContextConfigurer extends Specification {

    public static DockerComposeContainer composeContainer =
            new DockerComposeContainer(new File("src/test/resources/docker-compose.yaml"))
                    .waitingFor("postgres", Wait.forLogMessage(".*database system is ready to accept connections*\\s", 1))
                    .withLocalCompose(true)

    def setupSpec() {
        composeContainer.start()
    }

    def cleanupSpec() {
        composeContainer.stop()
    }
}
