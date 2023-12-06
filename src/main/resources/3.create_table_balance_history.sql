-- Active: 1697209273150@@localhost@5432@wallet
DROP TABLE IF EXISTS "balance_history";

CREATE TABLE IF NOT EXISTS "balance_history" (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    value DECIMAL DEFAULT 0,
    lastUpdate TIMESTAMP DEFAULT current_timestamp,
    accountId uuid REFERENCES "account"(id) NOT NULL
);
