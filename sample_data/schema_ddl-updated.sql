create table player
(
    id serial not null
        constraint player_pkey
            primary key,
    player_name text not null,
    x integer,
    y integer,
    hp integer,
    attack integer,
    armor integer,
    defence integer
);

create table game_state
(
    id serial not null
        constraint game_state_pkey
            primary key,
    current_map text not null,
    saved_at timestamp default CURRENT_TIMESTAMP not null,
    player_id integer not null
        constraint fk_player_id
            references player
);

create table inventory
(
    id serial not null
        constraint inventory_pk
            primary key,
    inventory text,
    player_id integer
        constraint fk_player_id
            references player
);

create unique index inventory_id_uindex
    on inventory (id);

create table keys
(
    id serial not null
        constraint keys_pk
            primary key,
    keys text,
    inventory_id integer
        constraint fk_inventory_id
            references inventory
);

create unique index keys_id_uindex
    on keys (id);