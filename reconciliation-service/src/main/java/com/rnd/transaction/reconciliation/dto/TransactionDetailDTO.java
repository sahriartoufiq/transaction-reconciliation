package com.rnd.transaction.reconciliation.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailDTO {

    @CsvBindByPosition(position = 0)
    private String profileName;

    @CsvBindByPosition(position = 1)
    private String transactionDate;

    @CsvBindByPosition(position = 2)
    private String transactionAmount;

    @CsvBindByPosition(position = 3)
    private String transactionNarrative;

    @CsvBindByPosition(position = 4)
    private String transactionDescription;

    @CsvBindByPosition(position = 5)
    private String transactionID;

    @CsvBindByPosition(position = 6)
    private String transactionType;

    @CsvBindByPosition(position = 7)
    private String walletReference;
}
