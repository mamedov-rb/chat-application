package com.rb.alwaysontheroad.chatservice.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonView({View.New.class})
public class ChatJoinDto {

    @Null(groups = Validation.Create.class)
    private UUID id;

    @NotNull
    private UUID chatId;

    @Null
    private UUID participantId;

    @Null
    private Instant createdAt;
}
