-- Active: 1697209273150@@localhost@5432@wallet
DROP TABLE IF EXISTS "account";

CREATE TABLE IF NOT EXISTS "account" (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    name VARCHAR(200) NOT NULL,
    creationdate TIMESTAMP DEFAULT current_timestamp,
    type VARCHAR(25) CHECK (type IN ('Cash', 'Bank', 'Mobile Money')),
    currencyid INT REFERENCES "currency"(id)
);