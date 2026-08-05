package com.banking_transaction_api.banking_api.domain.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import com.banking_transaction_api.banking_api.exceptions.BusinessException;

public enum FeeTable {
    SAME_DAY(0, 0, new BigDecimal("3.00"), new BigDecimal("0.025")),
    DAYS_1_TO_10(1, 10, new BigDecimal("12.00"), BigDecimal.ZERO),
    DAYS_11_TO_20(11, 20, BigDecimal.ZERO, new BigDecimal("0.082")),
    DAYS_21_TO_30(21, 30, BigDecimal.ZERO, new BigDecimal("0.069")),
    DAYS_31_TO_40(31, 40, BigDecimal.ZERO, new BigDecimal("0.047")),
    DAYS_41_TO_50(41, 50, BigDecimal.ZERO, new BigDecimal("0.017"));

    private final long minDay;
    private final long maxDay;
    private final BigDecimal fixedAmount;
    private final BigDecimal percentageAmount;

    FeeTable(long minDay, long maxDay, BigDecimal fixedAmount, BigDecimal percentageAmount) {
        this.minDay = minDay;
        this.maxDay = maxDay;
        this.fixedAmount = fixedAmount;
        this.percentageAmount = percentageAmount;
    }

    public static BigDecimal calculateFee(long diffDays, BigDecimal value) {
        FeeTable range = Arrays.stream(values())
                .filter(t -> diffDays >= t.minDay && diffDays <= t.maxDay)
                .findFirst()
                .orElseThrow(() -> new BusinessException("No fee applicable for this difference in days."));

        BigDecimal percentageValue = value.multiply(range.percentageAmount);
        BigDecimal totalFee = range.fixedAmount.add(percentageValue);

        return totalFee.setScale(2, RoundingMode.HALF_UP);
    }
}
