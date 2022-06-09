package com.rnd.transaction.reconciliation.service;

import com.rnd.transaction.reconciliation.dto.TransactionDetailDTO;
import com.rnd.transaction.reconciliation.dto.TrxComparisonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TrxComparisonService {

    private final TransactionHelperService helperService;

    public TrxComparisonDTO getTrxComparisonDTO(List<TransactionDetailDTO> dataList,
                                                List<TransactionDetailDTO> otherPartyDataList) {

        var trxIdDataMap = helperService.getTrxKeyDataMap(otherPartyDataList);
        var unmatchedTrxDetailList = helperService.getUnmatchedTrxDetailList(dataList, trxIdDataMap);
        var totalRecords = dataList.size();
        var unMatchedRecords = unmatchedTrxDetailList.size();

        return TrxComparisonDTO.builder()
                .totalRecords(totalRecords)
                .matchingRecords(totalRecords - unMatchedRecords)
                .unmatchedRecords(unMatchedRecords)
                .unmatchedRecordList(helperService.getUnmatchedRecordList(unmatchedTrxDetailList, otherPartyDataList))
                .build();
    }
}
