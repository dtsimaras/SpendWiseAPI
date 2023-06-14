package com.tsimo.spendwise.repository;

import com.tsimo.spendwise.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IncomeRepository extends JpaRepository<Income, Long> {
}