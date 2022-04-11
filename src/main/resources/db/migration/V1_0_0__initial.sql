create table if not exists chat_app_schema.chat_room
(
    id          uuid not null primary key,
    created_at  timestamp,
    name        varchar(255) unique,
    description text,
    updated_at  timestamp
);

alter table chat_app_schema.chat_room
    owner to chat_db_admin;


create table if not exists chat_app_schema.chat_participant
(
    id           uuid not null primary key,
    created_at   timestamp,
    updated_at   timestamp,
    user_id      uuid,
    chat_room_id uuid constraint chat_participant_chat_room_fk references chat_app_schema.chat_room not null
);

alter table chat_app_schema.chat_participant
    owner to chat_db_admin;
