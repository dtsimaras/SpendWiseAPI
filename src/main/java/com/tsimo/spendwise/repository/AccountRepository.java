package com.tsimo.spendwise.repository;

import com.tsimo.spendwise.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
