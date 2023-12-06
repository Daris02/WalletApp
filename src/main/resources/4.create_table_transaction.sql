-- Active: 1697209273150@@localhost@5432@wallet
DROP TABLE IF EXISTS "transaction";

CREATE TABLE IF NOT EXISTS "transaction" (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    label VARCHAR(250) NOT NULL,
    amount DECIMAL DEFAULT 0,
    transactiontype VARCHAR(25) CHECK (transactiontype IN ('DEBIT', 'CREDIT')),
    datetime TIMESTAMP DEFAULT current_timestamp,
    accountId uuid REFERENCES "account"(id) NOT NULL
);
