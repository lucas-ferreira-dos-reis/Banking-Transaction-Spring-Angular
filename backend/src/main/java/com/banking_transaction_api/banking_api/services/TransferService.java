package com.banking_transaction_api.banking_api.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.banking_transaction_api.banking_api.domain.dtos.TransferDto;
import com.banking_transaction_api.banking_api.domain.enums.FeeTable;
import com.banking_transaction_api.banking_api.domain.models.Transfer;
import com.banking_transaction_api.banking_api.exceptions.BusinessException;
import com.banking_transaction_api.banking_api.repositories.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Transactional
    public Transfer scheduleTransfer(TransferDto dto) {
        LocalDate creationDate = LocalDate.now();
        LocalDate scheduleDate = dto.getScheduledDate();

        if (scheduleDate.isBefore(creationDate)) {
            throw new BusinessException("The schedule date cannot be in the past!");
        }

        long diffDays = ChronoUnit.DAYS.between(creationDate, scheduleDate);

        BigDecimal calculatedFee;
        try {
            calculatedFee = FeeTable.calculateFee(diffDays, dto.getAmount());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(e.getMessage());
        }

        Transfer transfer = Transfer.builder()
                .sourceAccount(dto.getSourceAccount())
                .destinationAccount(dto.getDestinationAccount())
                .amount(dto.getAmount())
                .fee(calculatedFee)
                .creationDate(creationDate)
                .scheduleDate(scheduleDate)
                .build();

        return transferRepository.save(transfer);
    }

}
