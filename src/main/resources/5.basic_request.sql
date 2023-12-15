-- Active: 1697209273150@@localhost@5432@wallet

-- ACCOUNT
    -- Create account
        DO $$
        BEGIN
            BEGIN
                INSERT INTO "account" (name, account_type) VALUES ('test account', 'Cash');
                INSERT INTO "balance_history" (accountId) VALUES 
                    (
                        (SELECT id FROM "account" ORDER BY creationdate DESC LIMIT 1) -- <==> ACCOUNT_ID
                    );
            EXCEPTION
                WHEN OTHERS THEN
                    ROLLBACK;
                    RAISE;
            END;
            COMMIT;
        END $$;
    

-- TRANSACTION 
    -- CREDIT ACCOUNT
        DO $$
        BEGIN
            BEGIN
                UPDATE "account" SET balance = balance +  'AMOUNT' WHERE id = 'ACCOUNT_ID';
                INSERT INTO "balance_history" (value, accountId) VALUES 
                    (  
                        (SELECT balance FROM "account" WHERE id = 'ACCOUNT_ID'),
                        'ACCOUNT_ID'
                    );
                INSERT INTO "transaction" (label, amount, transactiontype, accountId) VALUES 
                    ('Salary', 'AMOUNT', 'CREDIT', 'ACCOUNT_ID');
            EXCEPTION
                WHEN OTHERS THEN
                    ROLLBACK;
                    RAISE;
            END;
            COMMIT;
        END $$;

        DO $$
        BEGIN
            BEGIN
                UPDATE "account" SET balance = balance +  'AMOUNT' WHERE id = 'ACCOUNT_ID';
                INSERT INTO "balance_history" (value, accountId) VALUES 
                    (  
                        (SELECT balance FROM "account" WHERE id = 'ACCOUNT_ID'),
                        'ACCOUNT_ID'
                    );
                INSERT INTO "transaction" (label, amount, transactiontype, accountId) VALUES 
                    ('Salary', 'AMOUNT', 'CREDIT', 'ACCOUNT_ID');
            EXCEPTION
                WHEN OTHERS THEN
                    ROLLBACK;
                    RAISE;
            END;
            COMMIT;
        END $$;


    -- DEBIT ACCOUNT
        DO $$
        BEGIN
            BEGIN
                UPDATE "account" SET balance = balance - 'AMOUNT' WHERE id = 'ACCOUNT_ID';
                INSERT INTO "balance_history" (value, accountId) VALUES 
                    ( 
                        (SELECT balance FROM "account" WHERE id = 'ACCOUNT_ID'),
                        'ACCOUNT_ID'
                    );
                INSERT INTO "transaction" (label, amount, transactiontype, accountId) VALUES 
                    ('Gift', 'AMOUNT', 'DEBIT', 'ACCOUNT_ID');
            EXCEPTION
                WHEN OTHERS THEN
                    ROLLBACK;
                    RAISE;
            END;
            COMMIT;
        END $$;


-- BALANCE HISTORY OF AN ACCOUNT
    SELECT
        bh.lastupdate,
        bh.value
    FROM "account" a
        INNER JOIN "balance_history" bh ON bh.accountid = a.id
    WHERE a.id = 'ACCOUNT_ID'; 


-- Sum of cash inflows and outflows between the given date range
    SELECT
        COALESCE(SUM(CASE WHEN tr.transactiontype = 'DEBIT' THEN value ELSE 0 END), 0) AS total_amount_spend,
        COALESCE(SUM(CASE WHEN tr.transactiontype = 'CREDIT' THEN value ELSE 0 END), 0) AS total_amount_income
    FROM "balance_history" bh
        INNER JOIN "account" acc ON acc.id = bh.accountid
        INNER JOIN "transaction" tr ON tr.accountid = acc.id
    WHERE bh.accountId = 'ACCOUNT_ID'
    AND updateDateTime BETWEEN 'START_DATE' AND 'END_DATE';

-- Sum of cash inflows and outflows between the given date range with transaction category
    SELECT c.id AS category_id,
       c.name AS category_name,
       (SUM(CASE WHEN bh.value IS NOT NULL THEN bh.value ELSE 0 END)) AS total_amount
    FROM "category" c
    LEFT JOIN "transaction" tr ON tr.categoryid = c.id
    LEFT JOIN "account" acc ON acc.id = tr.accountid
    LEFT JOIN "balance_history" bh ON bh.accountid = acc.id
        AND bh.accountId = 'ACCOUNT_ID'
        AND bh.updateDateTime BETWEEN  'START_DATE' AND 'END_DATE'
    GROUP BY c.id, c.name;