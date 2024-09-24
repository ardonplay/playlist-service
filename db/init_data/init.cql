CREATE KEYSPACE IF NOT EXISTS test_key_space WITH REPLICATION = { 'class': 'SimpleStrategy',
'replication_factor': 3 };

USE "test_key_space";

create table playlists
(
    id          uuid,
    user_id     uuid,
    description text,
    name        text,
    primary key (id, user_id)
);


create table playlists_tracks
(
    playlist_id   uuid,
    track_id      uuid,
    album_id      uuid,
    album_name    text,
    artist_id     uuid,
    artist_name   text,
    author_id     uuid,
    name          text,
    position      int,
    s3_cover_path text,
    s3_path       text,
    track_title   text,
    primary key (playlist_id, track_id)
);

create table tracks
(
    id            uuid primary key,
    album_id      uuid,
    album_name    text,
    artist_id     uuid,
    artist_name   text,
    name          text,
    s3_cover_path text,
    s3_path       text
);


CREATE MATERIALIZED VIEW playlists_by_user_id AS
    SELECT * FROM playlists WHERE user_id IS NOT NULL AND id IS NOT NULL
    PRIMARY KEY (user_id, id);




