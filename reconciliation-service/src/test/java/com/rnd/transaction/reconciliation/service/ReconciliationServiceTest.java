package com.rnd.transaction.reconciliation.service;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import com.rnd.transaction.reconciliation.dto.TrxComparisonDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ReconciliationServiceTest {

    @Mock
    private CsvProcessorService csvProcessorService;

    @Mock
    TrxComparisonService trxComparisonService;

    @InjectMocks
    private ReconciliationService reconciliationService;

    @Test
    @SneakyThrows
    public void givenValidValue_whenProcessValue_thenReturnReconciliationResponse() {
        var clientFile = getMockMultipartFile("client-report");
        var orgFile = getMockMultipartFile("org-report");
        var clientReportList = List.of(TransactionDetailDTO.builder().build());
        var orgReportList = List.of(TransactionDetailDTO.builder().build());

        Mockito.when(csvProcessorService.getRecordList(clientFile, TransactionDetailDTO.class))
                .thenReturn(clientReportList);
        Mockito.when(csvProcessorService.getRecordList(orgFile, TransactionDetailDTO.class))
                .thenReturn(orgReportList);
        Mockito.when(trxComparisonService.getTrxComparisonDTO(clientReportList, orgReportList))
                .thenReturn(TrxComparisonDTO.builder()
                        .totalRecords(10)
                        .matchingRecords(10)
                        .unmatchedRecords(0)
                        .unmatchedRecordList(new ArrayList<>())
                        .build());
        Mockito.when(trxComparisonService.getTrxComparisonDTO(clientReportList, orgReportList))
                .thenReturn(TrxComparisonDTO.builder()
                        .totalRecords(10)
                        .matchingRecords(10)
                        .unmatchedRecords(0)
                        .unmatchedRecordList(new ArrayList<>())
                        .build());

        var reconciliationResponse = reconciliationService.getTrxComparisonResponse(clientFile, orgFile);
        var clientReport = reconciliationResponse.getClientResult();
        var orgReport = reconciliationResponse.getOrgResult();

        Assertions.assertEquals(10, clientReport.getTotalRecords());
        Assertions.assertEquals(10, clientReport.getMatchingRecords());
        Assertions.assertEquals(0, clientReport.getUnmatchedRecords());
        Assertions.assertNotNull(clientReport.getUnmatchedRecordList());
        Assertions.assertTrue(clientReport.getUnmatchedRecordList().isEmpty());

        Assertions.assertEquals(10, orgReport.getTotalRecords());
        Assertions.assertEquals(10, orgReport.getMatchingRecords());
        Assertions.assertEquals(0, orgReport.getUnmatchedRecords());
        Assertions.assertNotNull(orgReport.getUnmatchedRecordList());
        Assertions.assertTrue(orgReport.getUnmatchedRecordList().isEmpty());

    }

    private MockMultipartFile getMockMultipartFile(String name) {
        var dummyBytes = new byte[]{};

        return new MockMultipartFile(
                name,
                name + ".csv",
                MediaType.TEXT_PLAIN_VALUE,
                dummyBytes
        );
    }
}
