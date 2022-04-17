create table chat_app_schema.chat
(
    id          uuid         not null primary key,
    name        varchar(255) not null,
    description text,
    type        varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);
alter table chat_app_schema.chat owner to chat_db_admin;


create table chat_app_schema.participant
(
    id         uuid not null primary key,
    user_id    uuid not null,
    created_at timestamp,
    updated_at timestamp
);
alter table chat_app_schema.participant owner to chat_db_admin;


create table chat_app_schema.chat_join
(
    id             uuid not null primary key,
    created_at     timestamp,
    chat_id        uuid constraint chat_join_chat_fk references chat_app_schema.chat,
    participant_id uuid constraint chat_join_participant_fk references chat_app_schema.participant
);
alter table chat_app_schema.chat_join owner to chat_db_admin;
