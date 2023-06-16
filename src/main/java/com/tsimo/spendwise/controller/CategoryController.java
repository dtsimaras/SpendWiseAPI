package com.tsimo.spendwise.controller;

import com.tsimo.spendwise.model.Category;
import com.tsimo.spendwise.repository.CategoryRepository;
import com.tsimo.spendwise.service.CategoryService;
import com.tsimo.spendwise.util.CategoryModelAssembler;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryRepository repository;
    private final CategoryModelAssembler assembler;
    private final CategoryService service;
    public CategoryController(CategoryRepository repository, CategoryModelAssembler assembler, CategoryService service) {
        this.assembler = assembler;
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<Category>> all() {
        List<EntityModel<Category>> categories = this.repository.findAll().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(categories,
                linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Category> one(@PathVariable long id) {
        return assembler.toModel(service.findByIdOrElseThrow(id));
    }

    @PostMapping
    ResponseEntity<?> createCategory(@Valid @RequestBody Category newCategory) {
        EntityModel<Category> model = assembler.toModel(this.repository.save(newCategory));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCategory(@Valid @RequestBody Category newCategory, @PathVariable long id) {
        Category updatedCategory = service.update(newCategory, id);
        EntityModel<Category> model = assembler.toModel(updatedCategory);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}