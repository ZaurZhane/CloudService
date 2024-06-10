CREATE TABLE IF NOT EXISTS files (
    filename VARCHAR(255) PRIMARY KEY,
    file_content BYTEA
);

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$12$mtWeu3YPK5gu0al3dg5xvu3SzJksXRossV4jTQzQ.nLJvSLxquPrC', 'admin')
ON CONFLICT (username) DO UPDATE SET username = 'admin';

INSERT INTO users (username, password, role)
VALUES ('user', '$2a$12$BuCXCtFnHqWkdKIVlyuSdO1WO3oNZ0arMWB7jQ5tdC1BNyQ7NAL2.', 'user')
ON CONFLICT (username) DO UPDATE SET username = 'user';
