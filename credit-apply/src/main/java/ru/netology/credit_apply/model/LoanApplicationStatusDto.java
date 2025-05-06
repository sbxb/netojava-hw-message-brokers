package ru.netology.credit_apply.model;

import ru.netology.credit_common.model.LoanApplicationStatus;

public record LoanApplicationStatusDto(
        int id,
        LoanApplicationStatus status
) {}
