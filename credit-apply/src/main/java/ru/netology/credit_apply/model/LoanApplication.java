package ru.netology.credit_apply.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LoanApplication {
    @Id
    @GeneratedValue
    public Integer id;
    public int loanAmount;
    public int loanTerm;
    public int applicantIncome;
    public int creditLoad;
    public String status;

    public LoanApplication(Integer id, int loanAmount, int loanTerm, int applicantIncome, int creditLoad, String status) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.applicantIncome = applicantIncome;
        this.creditLoad = creditLoad;
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
                ", status='" + status + '\'' +
                '}';
    }
}
