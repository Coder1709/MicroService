package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts , Long> {

    Optional<Accounts> findByCustomerId(Long customerID);


    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);

}
