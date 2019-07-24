CREATE TABLE claim_to (
    to_id bigserial
        CONSTRAINT claim_to_id_pkey PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE claim_from (
    from_id bigserial
        CONSTRAINT claim_from_id_pkey PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE claim_status (
    status_id bigserial
        CONSTRAINT claim_status_id_pkey PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE claim (
    claim_id bigserial
        CONSTRAINT claim_id_pkey PRIMARY KEY,
    claim_name text NOT NULL,
    claim_to_id bigint NOT NULL,
    claim_from_id bigint NOT NULL,
    claim_status_id bigint NOT NULL
);

ALTER TABLE claim
ADD CONSTRAINT claim_to_fkey FOREIGN KEY (claim_to_id) REFERENCES claim_to (to_id);

ALTER TABLE claim
ADD CONSTRAINT claim_from_id_fkey FOREIGN KEY (claim_from_id) REFERENCES claim_from (from_id);

ALTER TABLE claim
ADD CONSTRAINT claim_status_id_fkey FOREIGN KEY (claim_status_id) REFERENCES claim_status (status_id);

INSERT INTO claim_to (name) VALUES ('John');
INSERT INTO claim_to (name) VALUES ('Robert');
INSERT INTO claim_to (name) VALUES ('Michael');
INSERT INTO claim_to (name) VALUES ('David');
INSERT INTO claim_to (name) VALUES ('Mark');

INSERT INTO claim_from (name) VALUES ('Mary');
INSERT INTO claim_from (name) VALUES ('Maria');
INSERT INTO claim_from (name) VALUES ('Lisa');
INSERT INTO claim_from (name) VALUES ('Karen');
INSERT INTO claim_from (name) VALUES ('Carol');

INSERT INTO claim_status (name) VALUES ('Новая');
INSERT INTO claim_status (name) VALUES ('В обработке');
INSERT INTO claim_status (name) VALUES ('Обработана');
INSERT INTO claim_status (name) VALUES ('Отложена');
INSERT INTO claim_status (name) VALUES ('Завершена');

INSERT INTO claim (claim_name, claim_to_id, claim_from_id, claim_status_id)  VALUES ('Заявка1', 1, 1, 1);
INSERT INTO claim (claim_name, claim_to_id, claim_from_id, claim_status_id)  VALUES ('Заявка2', 2, 2, 2);
INSERT INTO claim (claim_name, claim_to_id, claim_from_id, claim_status_id)  VALUES ('Заявка3', 3, 3, 3);
INSERT INTO claim (claim_name, claim_to_id, claim_from_id, claim_status_id)  VALUES ('Заявка4', 4, 4, 4);
INSERT INTO claim (claim_name, claim_to_id, claim_from_id, claim_status_id)  VALUES ('Заявка5', 5, 5, 5);