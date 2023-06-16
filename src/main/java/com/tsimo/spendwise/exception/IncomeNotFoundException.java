package com.tsimo.spendwise.exception;

public class IncomeNotFoundException extends RuntimeException {
    public IncomeNotFoundException(long id) {
        super("Count not find income with id = " + id);
    }
}
