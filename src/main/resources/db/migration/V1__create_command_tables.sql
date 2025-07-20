CREATE TYPE "balance_type" AS ENUM (
  'Debit',
  'Credit'
);

CREATE TYPE "element" AS ENUM (
  'Assets',
  'Liabilities',
  'Equity',
  'Revenue',
  'Expenses',
  'Other'
);

CREATE TABLE "transactions" (
  "id" uuid PRIMARY KEY,
  "date" date NOT NULL,
  "description" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "entries" (
  "id" uuid PRIMARY KEY,
  "transaction_id" uuid NOT NULL,
  "coa" int NOT NULL,
  "amount" double precision NOT NULL,
  "type" balance_type NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "code_of_account" (
  "code" integer PRIMARY KEY NOT NULL,
  "title" varchar UNIQUE NOT NULL,
  "level" int NOT NULL,
  "element" element NOT NULL,
  "type" balance_type NOT NULL
);

ALTER TABLE "entries" ADD FOREIGN KEY ("coa") REFERENCES "code_of_account" ("code") ON DELETE SET NULL;
ALTER TABLE "entries" ADD FOREIGN KEY ("transaction_id") REFERENCES "transactions" ("id") ON DELETE CASCADE;