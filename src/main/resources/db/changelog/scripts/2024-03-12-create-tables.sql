CREATE TABLE IF NOT EXISTS my_user
(
    id                      UUID NOT NULL PRIMARY KEY,
    username                TEXT NOT NULL UNIQUE,
    password                TEXT NOT NULL,
    roles                   TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS audit
(
    id                      uuid NOT NULL PRIMARY KEY,
    datetime                timestamp WITH time zone NOT NULL,
    username                text NOT NULL,
    uri                     text NOT NULL,
    request_type            text NOT NULL
);