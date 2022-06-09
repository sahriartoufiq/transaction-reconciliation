package com.rnd.transaction.reconciliation.util;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;

import static com.rnd.transaction.reconciliation.util.StringUtils.getTrimmedLowerCaseValue;

public class TransactionUtils {

    public static String getTrxKeyWithTrxId(TransactionDetailDTO transaction) {
        return String.format("%s-%s",
                transaction.getTransactionID(),
                getTrimmedLowerCaseValue(transaction.getTransactionDescription()));
    }

    public static String getTrxKeyWithoutTrxId(TransactionDetailDTO transaction) {
        return String.format("%s-%s-%s-%s",
                getTrimmedLowerCaseValue(transaction.getWalletReference()),
                getTrimmedLowerCaseValue(transaction.getTransactionType()),
                getTrimmedLowerCaseValue(transaction.getTransactionAmount()),
                getTrimmedLowerCaseValue(transaction.getTransactionDate()));
    }
}
