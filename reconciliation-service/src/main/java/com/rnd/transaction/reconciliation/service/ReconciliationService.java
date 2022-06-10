package com.rnd.transaction.reconciliation.service;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import com.rnd.transaction.reconciliation.response.ReconciliationResponse;
import com.rnd.transaction.reconciliation.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReconciliationService {

    private final CsvProcessorService csvProcessorService;
    private final TrxComparisonService trxComparisonService;

    public ReconciliationResponse getTrxComparisonResponse(MultipartFile clientReport,
                                                           MultipartFile orgReport) {

        try {
            var clientReportDataList = csvProcessorService.getRecordList(clientReport,
                    TransactionDetailDTO.class);
            var orgReportDataList = csvProcessorService.getRecordList(orgReport,
                    TransactionDetailDTO.class);

            return ReconciliationResponse.builder()
                    .orgResult(trxComparisonService.getTrxComparisonDTO(orgReportDataList, clientReportDataList))
                    .clientResult(trxComparisonService.getTrxComparisonDTO(clientReportDataList, orgReportDataList))
                    .build();
        } catch (IOException ex) {
            log.error("Failed to retrieve reconciliation diff, error {}", ex.getMessage());
            throw new ApiException("Invalid file content!");
        }
    }
}
