CREATE TABLE urlshortenermapping (
id BIGINT PRIMARY KEY NOT NULL,
original_url VARCHAR(999) NOT NULL,
shortened_url VARCHAR(25) NOT NULL,
creation_time DATETIME NOT NULL
);