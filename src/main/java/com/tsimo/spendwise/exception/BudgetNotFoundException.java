package com.tsimo.spendwise.exception;

public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException(long id) {
        super("Count not find budget with id = " + id);
    }
}
