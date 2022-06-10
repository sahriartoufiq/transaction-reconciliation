package com.rnd.transaction.reconciliation.service;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import com.rnd.transaction.reconciliation.dto.UnmatchedRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.rnd.transaction.reconciliation.util.StringUtils.getTrimmedLowerCaseValue;

@Slf4j
@Service
public class TransactionHelperService {

    public List<TransactionDetailDTO> getUnmatchedTrxDetailList(List<TransactionDetailDTO> dataList,
                                                                Map<String, TransactionDetailDTO> trxIdDataMap) {

        return dataList.stream()
                .filter(trxData -> {
                    var key = getTrxKeyWithTrxId(trxData);

                    if (trxIdDataMap.containsKey(key)) {
                        trxIdDataMap.remove(key);

                        return false;
                    }

                    return true;
                })
                .collect(Collectors.toList());
    }

    public List<UnmatchedRecord> getUnmatchedRecordList(List<TransactionDetailDTO> unmatchedTrxDetailListList,
                                                        List<TransactionDetailDTO> otherPartyDataList) {

        var otherPartyDataMap = getTrxKeyDataMapByProbableKey(otherPartyDataList);

        return unmatchedTrxDetailListList.stream()
                .map(transaction -> UnmatchedRecord.builder()
                        .transactionId(transaction.getTransactionID())
                        .transactionDescription(transaction.getTransactionDescription())
                        .transactionDate(transaction.getTransactionDate())
                        .transactionAmount(getParsedValueString(transaction.getTransactionAmount()))
                        .walletReference(transaction.getWalletReference())
                        .probableMatchTrxId(otherPartyDataMap
                                .get(getTrxKeyWithoutTrxId(transaction)))
                        .build())
                .collect(Collectors.toList());
    }

    public Map<String, TransactionDetailDTO> getTrxKeyDataMap(List<TransactionDetailDTO> trxDataList) {
        return trxDataList
                .stream()
                .collect(Collectors
                        .toMap(this::getTrxKeyWithTrxId,
                                trxDetail -> trxDetail,
                                (trx1, trx2) -> trx1));
    }

    private Map<String, String> getTrxKeyDataMapByProbableKey(List<TransactionDetailDTO> trxDataList) {
        return trxDataList
                .stream()
                .collect(Collectors
                        .toMap(this::getTrxKeyWithoutTrxId,
                                TransactionDetailDTO::getTransactionID,
                                (trx1, trx2) -> trx1));
    }

    private String getTrxKeyWithTrxId(TransactionDetailDTO transaction) {
        return String.format("%s-%s",
                transaction.getTransactionID(),
                getTrimmedLowerCaseValue(transaction.getTransactionDescription()));
    }

    private String getTrxKeyWithoutTrxId(TransactionDetailDTO transaction) {
        return String.format("%s-%s-%s-%s",
                getTrimmedLowerCaseValue(transaction.getWalletReference()),
                getTrimmedLowerCaseValue(transaction.getTransactionType()),
                getTrimmedLowerCaseValue(getParsedValueString(transaction.getTransactionAmount())),
                getTrimmedLowerCaseValue(transaction.getTransactionDate()));
    }

    private String getParsedValueString(String transactionAmount) {
        try {
            return new BigDecimal(transactionAmount)
                    .stripTrailingZeros()
                    .toPlainString();
        } catch (Exception ex) {
            log.error("Failed to parse and convert transaction amount - {}, error - {}",
                    transactionAmount, ex.getMessage());

            return transactionAmount;
        }
    }
}
