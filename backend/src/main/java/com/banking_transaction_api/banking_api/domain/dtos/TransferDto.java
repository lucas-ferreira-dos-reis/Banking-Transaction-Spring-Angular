package com.banking_transaction_api.banking_api.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    @NotBlank(message = "The source account is required!")
    @Pattern(regexp = "^\\d{10}$", message = "The source account must contain exactly 10 numeric digits!")
    @Schema(description = "Source account number (10 number only digits)", example = "1234567890")
    private String sourceAccount;

    @NotBlank(message = "The destination account is required!")
    @Pattern(regexp = "^\\d{10}$", message = "The destination account must contain exactly 10 numeric digits!")
    @Schema(description = "Destination account number (10 number only digits)", example = "0987654321")
    private String destinationAccount;

    @NotNull(message = "The amount is required!")
    @Positive(message = "The transfer amount need to be more than zero!")
    @Schema(description = "Amount to be transferred", example = "1000.00")
    private BigDecimal amount;

    @NotNull(message = "The Scheduled Date is required!")
    @Schema(description = "Date when the transfer should take place (YYYY-MM-DD)", example = "2026-08-15")
    private LocalDate scheduledDate;

}
