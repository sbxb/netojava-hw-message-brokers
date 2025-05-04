package ru.netology.credit_apply.model;

public class ModelConvertor {
    public static LoanApplication dtoToLoanApplication(LoanApplicationDto dto) {
        return new LoanApplication(
                null,
                dto.loanAmount(),
                dto.loanTerm(),
                dto.applicantIncome(),
                dto.creditLoad(),
                "PENDING");
    }

    public static LoanApplicationProcessorEvent loanApplicationToEvent(LoanApplication la, double monthlyRate) {
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
}
