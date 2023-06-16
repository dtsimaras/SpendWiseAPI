package com.tsimo.spendwise.util;

import com.tsimo.spendwise.controller.BudgetController;
import com.tsimo.spendwise.model.Budget;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BudgetModelAssembler implements RepresentationModelAssembler<Budget, EntityModel<Budget>> {
    @Override
    public EntityModel<Budget> toModel(Budget budget) {
        return EntityModel.of(budget,
                linkTo(methodOn(BudgetController.class).one(budget.getId())).withSelfRel(),
                linkTo(methodOn(BudgetController.class).all()).withRel("budgets"));
    }
}