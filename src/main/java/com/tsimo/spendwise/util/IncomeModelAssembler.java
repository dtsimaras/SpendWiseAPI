package com.tsimo.spendwise.util;

import com.tsimo.spendwise.controller.IncomeController;
import com.tsimo.spendwise.model.Income;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class IncomeModelAssembler implements RepresentationModelAssembler<Income, EntityModel<Income>> {
    @Override
    public EntityModel<Income> toModel(Income income) {
        return EntityModel.of(income,
                linkTo(methodOn(IncomeController.class).one(income.getId())).withSelfRel(),
                linkTo(methodOn(IncomeController.class).all()).withRel("incomes"));
    }
}
