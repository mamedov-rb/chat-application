package com.rb.alwaysontheroad.chatservice.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonView({View.New.class})
@Accessors(chain = true)
public class MessageDto implements Serializable {

    @Null(groups = Validation.Create.class)
    private UUID senderId;

    @NotNull(groups = Validation.Create.class)
    private UUID chatId;

    @Null(groups = Validation.Create.class)
    private Instant createdAt;

    @NotBlank(groups = Validation.Create.class)
    private String payload;

    private static final long serialVersionUID = 1567023491000394675L;
}
