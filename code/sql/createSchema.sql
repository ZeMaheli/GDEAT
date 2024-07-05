create table users(
    id                  serial primary key,
    username            VARCHAR(64) unique  not null,
    password_hash       VARCHAR(256)        not null,
    email               VARCHAR(128) unique not null
);

create table refresh_tokens
(
    id              SERIAL PRIMARY KEY,
    token_hash VARCHAR(512) NOT NULL,
    user_id          int references users (id),
    expiration_date  TIMESTAMP NOT NULL,

    UNIQUE (user_id, token_hash)
);

CREATE TABLE revoked_access_tokens
(
    id              SERIAL PRIMARY KEY,
    user_id         INT           NOT NULL REFERENCES users (id),
    token_hash      VARCHAR(1024) NOT NULL,
    expiration_date TIMESTAMP     NOT NULL,

    UNIQUE (user_id, token_hash)
);

create table diagrams(
    id                  serial primary key,
    user_id         INT           NOT NULL REFERENCES users (id),
    name                VARCHAR(64),
    prompt              TEXT,
    data                JSONB,

    UNIQUE(name, user_id)
);

