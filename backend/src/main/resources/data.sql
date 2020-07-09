-- Database initial data and additional tables

-- Create tables to store oAuth2 tokens in database
CREATE TABLE IF NOT EXISTS oauth_access_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication BLOB,
    refresh_token VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication BLOB
);

--authority
insert ignore into authority(id, name) values (1, 'ROLE_USER');
insert ignore into authority(id, name) values (2, 'ROLE_ADMIN');
insert ignore into authority(id, name) values (3, 'ROLE_GUEST');

--specialization
insert ignore into specialization(id, name, type) values (1, 'Pediatrician', 'PEDIATRICIAN');
insert ignore into specialization(id, name, type) values (2, 'Surgeon', 'SURGEON');

