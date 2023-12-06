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


    SELECT id FROM "account" ORDER BY creationdate DESC LIMIT 0;
    

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
