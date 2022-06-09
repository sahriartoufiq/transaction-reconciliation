package com.rnd.transaction.reconciliation.util;

public class StringUtils {

    public static String getTrimmedLowerCaseValue(String value) {
        return value != null ? value.trim().toLowerCase() : "";
    }
}
