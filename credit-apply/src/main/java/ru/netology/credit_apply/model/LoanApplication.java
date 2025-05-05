package ru.netology.credit_apply.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LoanApplication {
    @Id
    @GeneratedValue
    private Integer id;
    private int loanAmount;
    private int loanTerm;
    private int applicantIncome;
    private int creditLoad;
    private int creditScore;
    private String status;

    public LoanApplication(Integer id, int loanAmount, int loanTerm, int applicantIncome, int creditLoad, int creditScore, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
