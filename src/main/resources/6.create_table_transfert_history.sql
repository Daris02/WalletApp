-- Active: 1697209273150@@localhost@5432@wallet
DROP TABLE IF EXISTS "transfert";

CREATE TABLE IF NOT EXISTS "transfert" (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    amount DECIMAL DEFAULT 0,
    datetime TIMESTAMP DEFAULT current_timestamp,
    debtorId uuid REFERENCES "account"(id) NOT NULL,
    creditorId uuid REFERENCES "account"(id) NOT NULL
);
