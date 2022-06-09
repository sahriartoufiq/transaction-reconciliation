package com.rnd.transaction.reconciliation.util;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionUtilsTest {

    @Test
    public void givenValidValue_whenProcessValue_thenReturnValueWithTrxId() {
        var transaction = getValidTransaction();
        var actualResult = TransactionUtils.getTrxKeyWithTrxId(transaction);
        var expectedResult = "0464012779360248-deduct";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void givenValidValue_whenProcessValue_thenReturnValueWithoutTrxId() {
        var transaction = getValidTransaction();
        var actualResult = TransactionUtils.getTrxKeyWithoutTrxId(transaction);
        var expectedResult = "p_nzi3ntg0ntdfmtm4njy2ndexny4zoday-1--5000-2014-01-13 01:38:56";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    private TransactionDetailDTO getValidTransaction() {
        return TransactionDetailDTO.builder()
                .profileName("Card Campaign")
                .transactionDate("2014-01-13 01:38:56")
                .transactionAmount("-5000")
                .transactionNarrative("SOUTH RING JOHANNESBURG BW")
                .transactionDescription("DEDUCT")
                .transactionID("0464012779360248")
                .transactionType("1")
                .walletReference("P_NzI3NTg0NTdfMTM4NjY2NDExNy4zODAy")
                .build();
    }
}
