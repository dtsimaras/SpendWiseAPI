package com.tsimo.spendwise.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EXPENSE")
public class Expense extends Transaction {}
