package ru.netology.credit_apply.exception;

public class LoanApplicationNotFound extends RuntimeException {
    public LoanApplicationNotFound(String message) {
        super(message);
    }
}
