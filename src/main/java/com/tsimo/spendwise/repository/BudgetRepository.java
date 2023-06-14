package com.tsimo.spendwise.repository;

import com.tsimo.spendwise.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
