create table refresh_token
(
    id         bigserial primary key,
    token_hash varchar(255) not null unique,
    user_id    bigint       not null references users (id),
    issued_at  timestamp    not null,
    expires_at timestamp    not null,
    revoked    boolean      not null default false
);

create index idx_refresh_token_user_id on refresh_token (user_id);
