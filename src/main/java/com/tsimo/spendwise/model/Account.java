package com.tsimo.spendwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Account extends TimestampedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(index = 1)
    private long id;
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain letters and numbers")
    @JsonProperty(index = 2)
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @JsonProperty(index = 3)
    @Column(unique = true)
    private String email;
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password can only contain letters and numbers")
    @NotBlank(message = "Password is mandatory")
    @JsonProperty(index = 4)
    private String password;
    @JsonProperty(index = 5)
    private boolean isEnabled;
}
