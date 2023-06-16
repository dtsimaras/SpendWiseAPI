package com.tsimo.spendwise.controller;

import com.tsimo.spendwise.model.Budget;
import com.tsimo.spendwise.repository.BudgetRepository;
import com.tsimo.spendwise.service.BudgetService;
import com.tsimo.spendwise.util.BudgetModelAssembler;
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
@RequestMapping("budgets")
public class BudgetController {
    private final BudgetRepository repository;
    private final BudgetModelAssembler assembler;
    private final BudgetService service;

    public BudgetController(BudgetRepository repository, BudgetModelAssembler assembler, BudgetService service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<Budget>> all() {
        List<EntityModel<Budget>> budgets = this.repository.findAll().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(budgets,
                linkTo(methodOn(BudgetController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Budget> one(@PathVariable long id) {
        return assembler.toModel(service.findByIdOrElseThrow(id));
    }

    @PostMapping
    ResponseEntity<?> createBudget(@Valid @RequestBody Budget newBudget) {
        EntityModel<Budget> model = assembler.toModel(service.create(newBudget));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBudget(@Valid @RequestBody Budget newBudget, @PathVariable long id) {
        EntityModel<Budget> model = assembler.toModel(service.update(newBudget, id));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBudget(@PathVariable long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
