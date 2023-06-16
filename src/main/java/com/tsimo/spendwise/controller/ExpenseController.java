package com.tsimo.spendwise.controller;

import com.tsimo.spendwise.model.Expense;
import com.tsimo.spendwise.repository.ExpenseRepository;
import com.tsimo.spendwise.service.ExpenseService;
import com.tsimo.spendwise.util.ExpenseModelAssembler;
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
@RequestMapping("expenses")
public class ExpenseController {
    private final ExpenseRepository repository;
    private final ExpenseModelAssembler assembler;
    private final ExpenseService service;

    public ExpenseController(ExpenseRepository repository, ExpenseModelAssembler assembler, ExpenseService service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<Expense>> all() {
        List<EntityModel<Expense>> expenses = this.repository.findAll()
                .stream().map(assembler::toModel).toList();
        return CollectionModel.of(expenses,
                linkTo(methodOn(ExpenseController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Expense> one(@PathVariable long id) {
        return assembler.toModel(service.findByIdOrElseThrow(id));
    }

    @PostMapping
    ResponseEntity<?> createExpense(@Valid @RequestBody Expense newExpense) {
        EntityModel<Expense> model = assembler.toModel(service.create(newExpense));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateExpense(@Valid @RequestBody Expense newExpense, @PathVariable long id) {
        EntityModel<Expense> model = assembler.toModel(service.update(newExpense, id));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteExpense(@PathVariable long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
