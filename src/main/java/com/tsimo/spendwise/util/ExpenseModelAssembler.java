package com.tsimo.spendwise.util;

import com.tsimo.spendwise.controller.ExpenseController;
import com.tsimo.spendwise.model.Expense;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExpenseModelAssembler implements RepresentationModelAssembler<Expense, EntityModel<Expense>> {
    @Override
    public EntityModel<Expense> toModel(Expense expense) {
        return EntityModel.of(expense,
                linkTo(methodOn(ExpenseController.class).one(expense.getId())).withSelfRel(),
                linkTo(methodOn(ExpenseController.class).all()).withRel("expenses"));
    }
}
