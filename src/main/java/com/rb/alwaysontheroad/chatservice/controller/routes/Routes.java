package com.rb.alwaysontheroad.chatservice.controller.routes;

public interface Routes {
    String ROOT_URL = "/api";

    String CHAT_ROOT_URL = ROOT_URL + "/chat";
    String ALL_CHAT_URL = ROOT_URL + "/chat/all";

    String CHAT_JOIN_ROOT_URL = ROOT_URL + "/chat-join";

    String MESSAGE_ROOT_URL = ROOT_URL + "/message";
    String PUBLIC_MESSAGE_ROOT_URL = MESSAGE_ROOT_URL + "/public";
}
