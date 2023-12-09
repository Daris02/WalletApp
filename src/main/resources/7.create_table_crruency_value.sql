-- Active: 1698301408453@@localhost@5432@wallet
DROP TABLE IF EXISTS "currency_value";

CREATE TABLE IF NOT EXISTS "currency_value" (
    id BIGSERIAL PRIMARY KEY,
    currency_source VARCHAR(100) NOT NULL,
    currency_destination VARCHAR(100) NOT NULL,
    amount DECIMAL NOT NULL,
    date_effect DATE
);

