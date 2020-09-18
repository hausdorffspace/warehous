package com.warehouse.demo.repository;

import com.warehouse.demo.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE bank_account AS b SET b.account_balance=:actualAccountBalance WHERE b.id = 1",nativeQuery = true)
    Integer updateBankAccount(@Param("actualAccountBalance") Integer actualAccountBalance);
}
