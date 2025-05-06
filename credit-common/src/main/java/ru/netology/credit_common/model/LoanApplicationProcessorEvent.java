package ru.netology.credit_common.model;

public record LoanApplicationProcessorEvent(
        int id,
        int loanAmount,
        int loanTerm,
        int applicantIncome,
        int creditLoad,
        double monthlyRate,
        LoanApplicationStatus status
) {}
