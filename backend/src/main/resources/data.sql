-- Database initial data and additional tables

-- Create tables to store oAuth2 tokens in database
CREATE TABLE IF NOT EXISTS oauth_access_token (
    token_id VARCHAR(255),
    token BYTEA,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication BYTEA,
    refresh_token VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
    token_id VARCHAR(255),
    token BYTEA,
    authentication BYTEA
);

--authority
insert into authority(id, name) values (1, 'ROLE_USER') ON CONFLICT DO NOTHING;
insert into authority(id, name) values (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
insert into authority(id, name) values (3, 'ROLE_GUEST') ON CONFLICT DO NOTHING;

--specialization
insert into specialization(id, name, type) values (1, 'Pediatrician', 'PEDIATRICIAN') ON CONFLICT DO NOTHING;
insert into specialization(id, name, type) values (2, 'Surgeon', 'SURGEON') ON CONFLICT DO NOTHING;

--doctors
INSERT ignore INTO doctor (id, uuid, first_name, last_name, specialization_id) VALUES ('1', '9a8adb14-c6c9-11ea-87d0-0242ac130003', 'Andrzej', 'Boryna', '1');
INSERT ignore INTO doctor (id, uuid, first_name, last_name, specialization_id) VALUES ('3', '9a8ade0c-c6c9-11ea-87d0-0242ac130003', 'Marzena', 'Budziszewska', '1');
INSERT ignore INTO doctor (id, uuid, first_name, last_name, specialization_id) VALUES ('2', '9a8add1c-c6c9-11ea-87d0-0242ac130003', 'Grzegorz', 'Dom', '2');
INSERT ignore INTO doctor (id, uuid, first_name, last_name, specialization_id) VALUES ('4', '9a8adede-c6c9-11ea-87d0-0242ac130003', 'Renata', 'Gruzin', '2');


