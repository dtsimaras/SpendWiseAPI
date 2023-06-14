package com.tsimo.spendwise.repository;

import com.tsimo.spendwise.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
