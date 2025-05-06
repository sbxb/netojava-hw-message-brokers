package ru.netology.credit_apply.model;

import ru.netology.credit_common.model.LoanApplicationProcessorEvent;
import ru.netology.credit_common.model.LoanApplicationStatus;

public class ModelConvertor {
    public static LoanApplication dtoToLoanApplication(LoanApplicationDto dto) {
        return new LoanApplication(
                null,
                dto.loanAmount(),
                dto.loanTerm(),
                dto.applicantIncome(),
                dto.creditLoad(),
                dto.creditScore(),
                LoanApplicationStatus.PENDING);
    }

    public static LoanApplicationProcessorEvent loanApplicationToEvent(LoanApplication la,
                                                                       double monthlyRate) {
        return new LoanApplicationProcessorEvent(
                la.getId(),
                la.getLoanAmount(),
                la.getLoanTerm(),
                la.getApplicantIncome(),
                la.getCreditLoad(),
                monthlyRate,
                la.getStatus()
        );
    }

    public static LoanApplicationStatusDto loanApplicationToStatusDto(LoanApplication la) {
        return new LoanApplicationStatusDto(la.getId(), la.getStatus());
    }
}
