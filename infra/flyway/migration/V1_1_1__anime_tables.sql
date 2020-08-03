CREATE TABLE anime
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       varchar(200),
    image_url  varchar(500),
    finished   boolean,
    site_urls  clob,
    created_at timestamp with time zone,
    added_by   bigint,
    foreign key (added_by) references users (id)
);

CREATE TABLE episodes
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    anime_id           bigint,
    name               varchar(200),
    released           boolean,
    length             int,
    links              clob,
    last_release_check timestamp with time zone,
    created_at         timestamp with time zone,
    added_by           bigint,
    foreign key (added_by) references users (id),
    foreign key (anime_id) references anime (id)
);

CREATE TABLE users_anime
(
    user_id  bigint,
    anime_id bigint,
    foreign key (user_id) references users (id),
    foreign key (anime_id) references anime (id)
);

CREATE TABLE users_episodes
(
    user_id    bigint,
    episode_id bigint,
    foreign key (user_id) references users (id),
    foreign key (episode_id) references episodes (id)
);
