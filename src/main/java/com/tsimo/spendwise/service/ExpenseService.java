package com.tsimo.spendwise.service;

import com.tsimo.spendwise.exception.ExpenseNotFoundException;
import com.tsimo.spendwise.model.Category;
import com.tsimo.spendwise.model.Expense;
import com.tsimo.spendwise.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryService categoryService) {
        this.expenseRepository = expenseRepository;
        this.categoryService = categoryService;
    }

    public Expense create(Expense newExpense) {
        Category category = categoryService.findByIdOrElseThrow(newExpense.getCategory().getId());
        newExpense.setCategory(category);
        return this.expenseRepository.save(newExpense);
    }

    public Expense update(Expense newExpense, long id) {
        Category category = categoryService.findByIdOrElseThrow(newExpense.getCategory().getId());

        return this.expenseRepository.findById(id)
                .map(expense -> {
                    expense.setAmount(newExpense.getAmount());
                    expense.setCategory(category);
                    expense.setName(newExpense.getName());
                    expense.setDetails(newExpense.getDetails());
                    return this.expenseRepository.save(expense);
                })
                .orElseThrow(()-> new ExpenseNotFoundException(id));
    }

    public Expense findByIdOrElseThrow(long id) {
        return this.expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException(id));
    }
}
