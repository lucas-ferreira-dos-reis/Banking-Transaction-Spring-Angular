package com.banking_transaction_api.banking_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banking_transaction_api.banking_api.domain.dtos.TransferDto;
import com.banking_transaction_api.banking_api.domain.models.Transfer;
import com.banking_transaction_api.banking_api.exceptions.BusinessException;
import com.banking_transaction_api.banking_api.repositories.TransferRepository;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferService transferService;

    private TransferDto validDto;

    @BeforeEach
    void setUp() {
        validDto = new TransferDto(
                "1234567890",
                "0987654321",
                new BigDecimal("1000.00"),
                LocalDate.now());
    }

    @Test
    @DisplayName("Should schedule transfer on same day (0 days) with $3.00 + 2.5% fee")
    void shouldCalculateFeeForSameDayTransfer() {
        validDto.setScheduledDate(LocalDate.now());

        when(transferRepository.save(any(Transfer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Transfer result = transferService.scheduleTransfer(validDto);

        assertNotNull(result);
        // $3.00 + 2.5% of $1000.00 ($25.00) = $28.00
        assertEquals(new BigDecimal("28.00"), result.getFee());
        verify(transferRepository, times(1)).save(any(Transfer.class));
    }

    @Test
    @DisplayName("Should schedule transfer between 1 and 10 days with $12.00 fee")
    void shouldCalculateFeeFor1To10Days() {
        validDto.setScheduledDate(LocalDate.now().plusDays(5));

        when(transferRepository.save(any(Transfer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Transfer result = transferService.scheduleTransfer(validDto);

        assertNotNull(result);
        // $12.00 + 0% = $12.00
        assertEquals(new BigDecimal("12.00"), result.getFee());
    }

    @Test
    @DisplayName("Should throw exception when source account is equal to destination account")
    void shouldThrowExceptionWhenSameAccounts() {
        validDto.setDestinationAccount(validDto.getSourceAccount());

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transferService.scheduleTransfer(validDto));

        assertEquals("The source account cannot be the same as the destination account!", exception.getMessage());
        verify(transferRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw exception when transfer date is in the past")
    void shouldThrowExceptionWhenDateInPast() {
        validDto.setScheduledDate(LocalDate.now().minusDays(1));

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transferService.scheduleTransfer(validDto));

        assertEquals("The schedule date cannot be in the past!", exception.getMessage());
        verify(transferRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw exception when transfer date is beyond 50 days")
    void shouldThrowExceptionWhenDateBeyond50Days() {
        validDto.setScheduledDate(LocalDate.now().plusDays(51));

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transferService.scheduleTransfer(validDto));

        assertTrue(exception.getMessage().contains("No fee applicable for this difference in days!"));
        verify(transferRepository, never()).save(any());
    }

}
