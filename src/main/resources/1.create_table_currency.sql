-- Active: 1697209273150@@localhost@5432@wallet
DROP DATABASE IF EXISTS "wallet";

CREATE DATABASE IF NO EXISTS "wallet";

-- -- -- -- -- -- -- -- -- -- -- -- --

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS "currency";

CREATE TABLE IF NOT EXISTS "currency" (
    id SERIAL PRIMARY KEY DEFAULT,
    name VARCHAR(25) NOT NULL,
    code VARCHAR(3) NOT NULL
);

INSERT INTO "currency" (name, code) values ('Ariary', 'MGA'), ('Euro', 'EUR');