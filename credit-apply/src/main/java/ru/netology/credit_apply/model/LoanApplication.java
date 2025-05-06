package ru.netology.credit_apply.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.netology.credit_common.model.LoanApplicationStatus;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int loanAmount;
    private int loanTerm;
    private int applicantIncome;
    private int creditLoad;
    private int creditScore;
    private LoanApplicationStatus status;

    public LoanApplication(Integer id, int loanAmount, int loanTerm, int applicantIncome,
                           int creditLoad, int creditScore, LoanApplicationStatus status) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.applicantIncome = applicantIncome;
        this.creditLoad = creditLoad;
        this.creditScore = creditScore;
        this.status = status;
    }

    public LoanApplication() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public int getApplicantIncome() {
        return applicantIncome;
    }

    public void setApplicantIncome(int applicantIncome) {
        this.applicantIncome = applicantIncome;
    }

    public int getCreditLoad() {
        return creditLoad;
    }

    public void setCreditLoad(int creditLoad) {
        this.creditLoad = creditLoad;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(LoanApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", loanAmount=" + loanAmount +
                ", loanTerm=" + loanTerm +
                ", applicantIncome=" + applicantIncome +
                ", creditLoad=" + creditLoad +
                ", creditScore=" + creditScore +
                ", status='" + status + '\'' +
                '}';
    }
}
