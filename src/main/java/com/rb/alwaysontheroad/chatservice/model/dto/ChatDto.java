package com.rb.alwaysontheroad.chatservice.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.chatservice.model.enums.ChatType;
import com.rb.alwaysontheroad.chatservice.shared.validation.Validation;
import com.rb.alwaysontheroad.chatservice.shared.view.View;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@JsonView({View.New.class})
@Getter
@Setter
@ToString
public class ChatDto implements Serializable {

    @Null(groups = Validation.Create.class)
    @NotNull(groups = Validation.Update.class)
    private UUID id;

    @NotBlank(groups = Validation.Create.class)
    private String name;

    private String description;

    @NotNull(groups = Validation.Create.class)
    private ChatType type;

    @Null(groups = {Validation.Create.class, Validation.Update.class})
    private Instant createdAt;

    @Null(groups = {Validation.Create.class, Validation.Update.class})
    private Instant updatedAt;

    private static final long serialVersionUID = 2567653491060394677L;
}

