package com.tsimo.spendwise.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("INCOME")
public class Income extends Transaction {}
