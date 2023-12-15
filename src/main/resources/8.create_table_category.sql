-- Active: 1698301408453@@localhost@5432@wallet
DROP TABLE IF EXISTS "category";

CREATE TABLE IF NOT EXISTS "category" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL UNIQUE
);

INSERT INTO "category" (name) VALUES
('Food & Drinks'), 
        ('Bar, cafe'), ('Groceries'), ('Restaurant, fast-food'),
('Shooping'), 
        ('Clothes & shoes'), ('Drug-store, chemist'), ('Electronics, accessories'),
        ('Free time'), ('Gifts, joy'), ('Health and beauty'), ('Home, garden'), ('Kids'),
        ('Pets, animals'), ('Stationery, tools'),
('Housing'), 
        ('Energy, utilities'), ('Maintenance, repairs'), ('Mortgage'), ('Property insurance'),
        ('Rent'), ('Services'),
('Transportation'),
        ('Business trips'), ('Long distance'), ('Public transport'), ('Taxi'),
('Vehicle'),
        ('Fuel'), ('Leasing'), ('Parking'), ('Rentals'), ('Vehicle insurance'), ('Vehicle maintenance'),
('Life & Entertaiment'),
        ('Active sport, fitness'), ('Alcohol, tobacco'), ('Books, audio, subscriptions'),
        ('Charity, gifts'), ('Culture, sport events'), ('Educations, development'),
        ('Health car, doctor'), ('Hobbies'), ('Holday, trips, hotels'), ('Life events'),
        ('Lottery, gambling'), ('TV, Streaming'), ('Wellness, beauty'),
('Communication, PC'),
        ('Internet'), ('Phone, mobile phone'), ('Postal services'), ('Software, apps, games'),
('Finacial expenses'),
        ('Advisory'), ('Charges, Fees'), ('Child Support'), ('Fines'), ('Insurances'), ('Loan, intersts'), ('Taxes'),
('Investments'),
        ('Collections'), ('Financial investments'), ('Realty'), ('Savings'), ('Vehicle, chattels'),
('Income'),
        ('Lending, renting'), ('Refunds (fax, purchase)'), ('Rental income'), ('Sale'), ('Wage, invoices');
