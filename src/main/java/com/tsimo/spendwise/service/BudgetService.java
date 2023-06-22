package com.tsimo.spendwise.service;

import com.tsimo.spendwise.exception.BudgetNotFoundException;
import com.tsimo.spendwise.model.Budget;
import com.tsimo.spendwise.model.Category;
import com.tsimo.spendwise.repository.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryService categoryService;

    public BudgetService(BudgetRepository budgetRepository, CategoryService categoryService) {
        this.budgetRepository = budgetRepository;
        this.categoryService = categoryService;
    }

    public Budget create(Budget newBudget) {
        Category category = categoryService.findByIdOrElseThrow(newBudget.getCategory().getId());
        newBudget.setCategory(category);
        return this.budgetRepository.save(newBudget);
    }

    public Budget update(Budget newBudget, long id) {
        Category category = categoryService.findByIdOrElseThrow(newBudget.getCategory().getId());

        return this.budgetRepository.findById(id)
                .map(budget -> {
                    budget.setAmount(newBudget.getAmount());
                    budget.setCategory(category);
                    budget.setMonth(budget.getMonth());
                    budget.setYear(budget.getYear());
                    return this.budgetRepository.save(budget);
                })
                .orElseThrow(() -> new BudgetNotFoundException(id));
    }

    public Budget findByIdOrElseThrow(long id) {
        return this.budgetRepository.findById(id).orElseThrow(() -> new BudgetNotFoundException(id));
    }


}
