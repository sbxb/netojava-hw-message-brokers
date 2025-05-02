package ru.netology.credit_apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.credit_apply.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer> {

}
