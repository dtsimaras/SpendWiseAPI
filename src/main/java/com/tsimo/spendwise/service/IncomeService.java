package com.tsimo.spendwise.service;

import com.tsimo.spendwise.exception.IncomeNotFoundException;
import com.tsimo.spendwise.model.Category;
import com.tsimo.spendwise.model.Income;
import com.tsimo.spendwise.repository.IncomeRepository;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final CategoryService categoryService;

    public IncomeService(IncomeRepository incomeRepository, CategoryService categoryService) {
        this.incomeRepository = incomeRepository;
        this.categoryService = categoryService;
    }

    public Income create(Income newIncome) {
        Category category = categoryService.findByIdOrElseThrow(newIncome.getCategory().getId());
        newIncome.setCategory(category);
        return this.incomeRepository.save(newIncome);
    }

    public Income update(Income newIncome, long id) {
        Category category = categoryService.findByIdOrElseThrow(newIncome.getCategory().getId());

        return this.incomeRepository.findById(id)
                .map(income -> {
                    income.setAmount(newIncome.getAmount());
                    income.setCategory(category);
                    income.setName(newIncome.getName());
                    income.setDetails(newIncome.getDetails());
                    return this.incomeRepository.save(income);
                })
                .orElseThrow(()-> new IncomeNotFoundException(id));
    }

    public Income findByIdOrElseThrow(long id) {
        return this.incomeRepository.findById(id).orElseThrow(()-> new IncomeNotFoundException(id));
    }
}
