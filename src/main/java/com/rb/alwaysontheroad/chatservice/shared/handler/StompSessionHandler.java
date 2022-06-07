//package com.rb.alwaysontheroad.chatservice.shared.handler;
//
//import com.rb.alwaysontheroad.chatservice.shared.event.Routes;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.messaging.simp.stomp.StompHeaders;
//import org.springframework.messaging.simp.stomp.StompSession;
//import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
//
//@Slf4j
//public class StompSessionHandler extends StompSessionHandlerAdapter {
//
//    @Override
//    public void afterConnected(StompSession session, @NotNull StompHeaders connectedHeaders) {
//        log.info("Session established, id: '{}'", session.getSessionId());
//        session.subscribe(Routes.TOPIC_DESTINATION_ROOT, this);
//        log.info("Subscribed to topic: '{}'", Routes.TOPIC_DESTINATION_ROOT);
//    }
//
//    @Override
//    public void handleFrame(@NotNull StompHeaders headers, Object payload) {
//        log.info("Received payload: {}", payload);
//    }
//}
