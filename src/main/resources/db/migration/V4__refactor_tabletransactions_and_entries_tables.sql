ALTER TABLE transactions
    RENAME TO ledgers;

ALTER TABLE entries
    RENAME TO ledger_items;

ALTER TABLE ledger_items
    RENAME COLUMN transaction_id TO ledger_id;