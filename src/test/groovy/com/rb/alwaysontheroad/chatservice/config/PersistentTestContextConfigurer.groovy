package com.rb.alwaysontheroad.chatservice.config

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles(["persistent-test"])
@Testcontainers
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
abstract class PersistentTestContextConfigurer extends Specification {

    public static DockerComposeContainer composeContainer =
            new DockerComposeContainer(new File("src/test/resources/persistent-test-docker-compose.yaml"))
                    .waitingFor("postgres", Wait.forLogMessage(".*database system is ready to accept connections*\\s", 1))
                    .withLocalCompose(true)

    def setupSpec() {
        composeContainer.start()
    }

    def cleanupSpec() {
        composeContainer.stop()
    }
}
