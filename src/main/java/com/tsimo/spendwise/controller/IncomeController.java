package com.tsimo.spendwise.controller;

import com.tsimo.spendwise.model.Income;
import com.tsimo.spendwise.repository.IncomeRepository;
import com.tsimo.spendwise.service.IncomeService;
import com.tsimo.spendwise.util.IncomeModelAssembler;
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
@RequestMapping("incomes")
public class IncomeController {
    private final IncomeRepository repository;
    private final IncomeModelAssembler assembler;
    private final IncomeService service;

    public IncomeController(IncomeRepository repository, IncomeModelAssembler assembler, IncomeService service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<Income>> all() {
        List<EntityModel<Income>> incomes = this.repository.findAll()
                .stream().map(assembler::toModel).toList();
        return CollectionModel.of(incomes,
                linkTo(methodOn(IncomeController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Income> one(@PathVariable long id) {
        return assembler.toModel(service.findByIdOrElseThrow(id));
    }

    @PostMapping
    ResponseEntity<?> createIncome(@Valid @RequestBody Income newIncome) {
        EntityModel<Income> model = assembler.toModel(service.create(newIncome));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateIncome(@Valid @RequestBody Income newIncome, @PathVariable long id) {
        EntityModel<Income> model = assembler.toModel(service.update(newIncome, id));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteIncome(@PathVariable long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
