-- Active: 1698301408453@@localhost@5432@wallet
DROP TABLE IF EXISTS "currency_value";

CREATE TABLE IF NOT EXISTS "currency_value" (
    id BIGSERIAL PRIMARY KEY,
    currency_source INT REFERENCES "currency"(id),
    currency_destination INT REFERENCES "currency"(id),
    amount DECIMAL NOT NULL,
    date_effect TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO "currency_value" (currency_source, currency_destination, amount) VALUES
(2, 1, 4_500),
(2, 1, 4_600);
