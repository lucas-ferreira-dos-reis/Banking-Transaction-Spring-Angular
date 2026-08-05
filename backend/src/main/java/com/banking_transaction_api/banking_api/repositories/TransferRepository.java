package com.banking_transaction_api.banking_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking_transaction_api.banking_api.domain.models.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
