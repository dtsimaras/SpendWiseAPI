package com.tsimo.spendwise.repository;

import com.tsimo.spendwise.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
