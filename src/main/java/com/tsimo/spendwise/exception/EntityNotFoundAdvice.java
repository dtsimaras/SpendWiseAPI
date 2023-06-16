package com.tsimo.spendwise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler({CategoryNotFoundException.class, BudgetNotFoundException.class,
            ExpenseNotFoundException.class, IncomeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundHandler(RuntimeException ex) {
        return ex.getMessage();
    }
}
