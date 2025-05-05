--liquibase formatted sql

--changeset credit-apply:create-table-applications
CREATE TABLE IF NOT EXISTS loan_applications (
    id SERIAL PRIMARY KEY,
    applicant_income INTEGER NOT NULL,
    credit_load INTEGER NOT NULL,
    credit_score INTEGER NOT NULL,
    loan_amount INTEGER NOT NULL,
    loan_term INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL
);
