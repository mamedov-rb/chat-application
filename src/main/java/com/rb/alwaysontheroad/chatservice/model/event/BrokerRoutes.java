package com.rb.alwaysontheroad.chatservice.model.event;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface BrokerRoutes {
    String WS_PATH = "/stomp-broker";
    String APP_DESTINATION_PREFIX = "/app";
    String TOPIC_DESTINATION_ROOT = "/topic";
    String QUEUE_DESTINATION_ROOT = "/queue";
    String UNRESOLVED_USERS_TOPIC = TOPIC_DESTINATION_ROOT + "/unresolved.users.destination";
    String REGISTRY_BROADCAST_TOPIC = TOPIC_DESTINATION_ROOT + "/registry.broadcast";

    @NotNull
    static String getPublicMessagesQueueRoute(@NotNull UUID chatId) {
        return  "/chat/" + chatId + "/messages";
    }

    @NotNull
    static String getPrivateMessagesQueueRoute(@NotNull UUID chatId) {
        return QUEUE_DESTINATION_ROOT + "/" + chatId + ".private.messages";
    }

    @NotNull
    static String getConnectedUsersTopicRoute(@NotNull UUID chatId) {
        return TOPIC_DESTINATION_ROOT + "/" + chatId + ".connected.users";
    }
}
