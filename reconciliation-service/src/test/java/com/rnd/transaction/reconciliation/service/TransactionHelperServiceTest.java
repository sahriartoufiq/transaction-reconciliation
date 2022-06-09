package com.rnd.transaction.reconciliation.service;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class TransactionHelperServiceTest {

    @InjectMocks
    private TransactionHelperService transactionHelperService;

    @Test
    public void givenValidValue_whenProcessValue_thenReturnUnmatchedTrxList() {
        var unmatchedTrxDetailList = transactionHelperService
                .getUnmatchedTrxDetailList(getDataList(), getOtherPartyDataMap());

        Assertions.assertEquals(3, unmatchedTrxDetailList.size());
    }

    @Test
    public void givenValidValue_whenProcessValue_thenReturnUnmatchedRecordList() {
        var unmatchedRecordList = transactionHelperService
                .getUnmatchedRecordList(getUnmatchedDataList(), getOtherPartyDataList());

        Assertions.assertEquals(2, unmatchedRecordList.size());
    }

    @Test
    public void givenValidValue_whenProcessValue_thenReturnValidDataMapWithoutDuplication() {
        var trxKeyDataMap = transactionHelperService.getTrxKeyDataMap(getDataList());

        Assertions.assertEquals(4, trxKeyDataMap.size());
    }

    private List<TransactionDetailDTO> getDataList() {
        return List.of(getTransaction("0384012056029314", "2014-01-11 22:27:44"),
                getTransaction("0384012056029315", "2014-01-11 23:27:44"),
                getTransaction("0384012056029316", "2014-01-11 23:35:44"),
                getTransaction("0384012056029317", "2014-01-11 23:58:44"),
                getTransaction("0384012056029317", "2014-01-11 23:58:44"));
    }

    private List<TransactionDetailDTO> getUnmatchedDataList() {
        return List.of(getTransaction("0384012056029315", "2014-01-11 23:27:44"),
                getTransaction("0384012056029317", "2014-01-11 23:58:44"));
    }

    private List<TransactionDetailDTO> getOtherPartyDataList() {
        return List.of(getTransaction("0384012056029314", "2014-01-11 22:27:44"),
                getTransaction("0384012056029316", "2014-01-11 23:35:44"));
    }

    private Map<String, TransactionDetailDTO> getOtherPartyDataMap() {
        Map<String, TransactionDetailDTO> dataMap = new HashMap<>();

        dataMap.put("0384012056029314-deduct", getTransaction("0384012056029314", "2014-01-11 22:27:44"));
        dataMap.put("0384012056029316-deduct", getTransaction("0384012056029316", "2014-01-11 23:35:44"));

        return dataMap;
    }

    private TransactionDetailDTO getTransaction(String trxId, String trxDate) {
        return TransactionDetailDTO.builder()
                .transactionID(trxId)
                .transactionDescription("DEDUCT")
                .transactionDate(trxDate)
                .walletReference("P_NzI1MjA1NjZfMTM3ODczODI3Mi4wNzY5")
                .transactionAmount("-500")
                .build();
    }
}
