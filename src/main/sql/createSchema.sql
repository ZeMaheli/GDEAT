create table users(
    id                  serial primary key,
    username            VARCHAR(64) unique  not null,
    password_hash       VARCHAR(256)        not null,
    email               VARCHAR(320) unique not null
);

create table refresh_tokens
(
    token_hash VARCHAR(512) primary key,
    user_id          int references users (id),
    expiration_date  TIMESTAMP NOT NULL,

    UNIQUE (user_id, token_hash)
);

create table graphs(
    id                  serial primary key,
    xml_content         xml
);

create table users_graphs(
    id                  serial primary key,
    graph_id            int not null,
    user_id             int not null,

    CONSTRAINT user_identification FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT graph_identification FOREIGN KEY (graph_id) REFERENCES graphs (id),
    UNIQUE(graph_id, user_id)
);
