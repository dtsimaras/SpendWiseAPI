package com.tsimo.spendwise.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(long id) {
        super("Count not find category with id = " + id);
    }
}
