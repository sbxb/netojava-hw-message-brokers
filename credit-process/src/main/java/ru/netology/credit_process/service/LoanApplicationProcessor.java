package ru.netology.credit_process.service;

import org.springframework.stereotype.Service;
import ru.netology.credit_process.model.LoanApplicationProcessorEvent;

@Service
public class LoanApplicationProcessor {
    public boolean approved(LoanApplicationProcessorEvent lape) {
        int monthlyPayment = calculateMonthlyPayment(lape);
        double debtToIncomeRatio = (monthlyPayment + lape.creditLoad()) / (double) lape.applicantIncome();
        System.out.println(">>> got " + lape);
        System.out.println(">>> monthly payment: " + monthlyPayment);
        System.out.println(">>> ratio: " + debtToIncomeRatio);
        return debtToIncomeRatio < 0.5;
    }

    private int calculateMonthlyPayment(LoanApplicationProcessorEvent lape) {
        // K = (M * (1 + M) ^ S) / ((1 + M) ^ S - 1)
        // K - Коэффициент аннуитета
        // M - месячная процентная ставка по кредиту (например 0.0125, если 15% годовых)
        // S - срок кредита в месяцах
        double k = lape.monthlyRate() * Math.pow(1.0 + lape.monthlyRate(), lape.loanTerm()) / (Math.pow(1.0 + lape.monthlyRate(), lape.loanTerm()) - 1.0);

        return (int) (lape.loanAmount() * k);
        /*
            Допустим, вы берете в кредит 2 млн рублей на 5 лет по ставке 15% годовых
            К = (0,0125 * (1 + 0,0125) ^ 60) / ((1 + 0,0125) ^ 60 - 1) = 0,02379
            Х = 2 000 000 * К
            Получаем ежемесячный платеж 47 580 рублей
        */
    }
}
