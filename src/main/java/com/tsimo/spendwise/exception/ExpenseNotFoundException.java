package com.tsimo.spendwise.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(long id) {
        super("Count not find expense with id = " + id);
    }
}
