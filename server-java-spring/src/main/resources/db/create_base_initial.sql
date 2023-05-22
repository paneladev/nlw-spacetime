CREATE TABLE "user" (
    id serial PRIMARY KEY,
    name varchar(255) not null,
    login varchar(255) not null,
    git_hub_id int,
    avatar_url varchar(255)
);

CREATE TABLE memory (
    id serial PRIMARY KEY,
    content text,
    cover_url varchar(255),
    is_public boolean default false,
    created_at timestamp,
    user_id int,
    CONSTRAINT fk_memory_user FOREIGN KEY(user_id) REFERENCES "user"(id)
);