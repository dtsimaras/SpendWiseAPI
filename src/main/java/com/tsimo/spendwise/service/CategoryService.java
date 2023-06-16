package com.tsimo.spendwise.service;

import com.tsimo.spendwise.exception.CategoryNotFoundException;
import com.tsimo.spendwise.model.Category;
import com.tsimo.spendwise.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category update(Category newCategory, long id) {
        return this.categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return this.categoryRepository.save(category);
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category findByIdOrElseThrow(long id) {
        return this.categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
    }
}
