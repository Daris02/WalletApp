-- Active: 1698301408453@@localhost@5432@wallet
DROP TABLE IF EXISTS "currency_value";

CREATE TABLE IF NOT EXISTS "currency_value" (
    id BIGSERIAL PRIMARY KEY,
    currency_source INT REFERENCES "currency"(id),
    currency_destination INT REFERENCES "currency"(id),
    amount DECIMAL NOT NULL,
    date_effect DATE
);

INSERT INTO "currency_value" (currency_source, currency_destination, amount, date_effect) VALUES
(2, 1, 4_500, '2023-12-10'),
(2, 1, 4_600, '2023-12-11');
