package com.omegaeducacional.caixa.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashUtils {
    /**
     *
     * @param inputBalance the balance to apply the mask
     * @return double value in mask format x.xx
     * @throws RuntimeException when balance is negative or null
     */
    public static Double maskBalance(Double inputBalance) {
        if (inputBalance == null) {
            throw new RuntimeException("Balance is null! Why?");
        }

        double balance = BigDecimal.valueOf(inputBalance)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

//        if (balance < 0) {
//            throw new RuntimeException("Balance can't be negative!");
//        }

        return balance;
    }
}

