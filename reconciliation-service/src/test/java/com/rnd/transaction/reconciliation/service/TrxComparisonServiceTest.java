package com.rnd.transaction.reconciliation.service;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class TrxComparisonServiceTest {

    @Mock
    private TransactionHelperService helperService;

    @InjectMocks
    private TrxComparisonService trxComparisonService;

    @Test
    public void givenValidValue_whenProcessValue_thenReturnTrxComparisonDTO() {
        var unmatchedTrxList = List.of(getTransaction("0384012056029315"));
        Mockito.when(helperService.getUnmatchedTrxDetailList(Mockito.any(), Mockito.anyMap()))
                .thenReturn(unmatchedTrxList);

        var trxComparisonData = trxComparisonService.getTrxComparisonDTO(getDataList(), getOtherPartyDataList());

        Assertions.assertNotNull(trxComparisonData);
        Assertions.assertEquals(2, trxComparisonData.getTotalRecords());
        Assertions.assertEquals(1, trxComparisonData.getMatchingRecords());
        Assertions.assertEquals(1, trxComparisonData.getUnmatchedRecords());
    }

    private List<TransactionDetailDTO> getDataList() {
        return List.of(getTransaction("0384012056029314"),
                getTransaction("0384012056029315"));
    }

    private List<TransactionDetailDTO> getOtherPartyDataList() {
        return List.of(getTransaction("0384012056029314"));
    }

    private TransactionDetailDTO getTransaction(String transactionId) {
        return TransactionDetailDTO.builder()
                .transactionID(transactionId)
                .build();
    }
}
