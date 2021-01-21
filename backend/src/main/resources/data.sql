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

