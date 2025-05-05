package ru.netology.credit_apply.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record LoanApplicationDto(
        @Min(1_000)
        @Max(2_000_000_000)
        int loanAmount,

        @Min(1)
        @Max(420)
        int loanTerm,

        @Min(1_000)
        @Max(1_000_000_000)
        int applicantIncome,

        @Min(0)
        @Max(1_000_000_000)
        int creditLoad,

        @Min(1)
        @Max(999)
        int creditScore
) {}
