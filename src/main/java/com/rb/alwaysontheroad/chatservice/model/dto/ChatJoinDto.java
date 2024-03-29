package com.rb.alwaysontheroad.chatservice.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonView({View.New.class})
public class ChatJoinDto implements Serializable {

    @Null(groups = Validation.Create.class)
    private UUID id;

    @NotNull(groups = Validation.Create.class)
    private UUID chatId;

    @Null(groups = Validation.Create.class)
    private UUID participantId;

    @Null(groups = Validation.Create.class)
    private Instant createdAt;

    private static final long serialVersionUID = 2577753491060394677L;
}
