package com.banking_transaction_api.banking_api.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    @NotBlank(message = "The source account is required!")
    @Pattern(regexp = "^\\d{10}$", message = "The source account must contain exactly 10 numeric digits!")
    private String sourceAccount;

    @NotBlank(message = "The destination account is required!")
    @Pattern(regexp = "^\\d{10}$", message = "The destination account must contain exactly 10 numeric digits!")
    private String destinationAccount;

    @NotNull(message = "The value is required!")
    @Positive(message = "The transfer value need to be more than zero!")
    private BigDecimal value;

    @NotNull(message = "The Scheduled Date is required!")
    private LocalDate scheduledDate;

}
