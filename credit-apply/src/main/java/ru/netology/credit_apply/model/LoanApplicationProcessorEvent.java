package ru.netology.credit_apply.model;

public record LoanApplicationProcessorEvent(
        int id,
        int loanAmount,
        int loanTerm,
        int applicantIncome,
        int creditLoad,
        double monthlyRate,
        String status
) {}
