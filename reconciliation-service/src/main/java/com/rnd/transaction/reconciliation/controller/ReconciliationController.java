package com.rnd.transaction.reconciliation.controller;

import com.rnd.transaction.reconciliation.service.ReconciliationService;
import com.rnd.transaction.reconciliation.dto.response.ReconciliationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReconciliationController {

    private final ReconciliationService reconciliationService;

    @CrossOrigin
    @PostMapping("/api/transactions/compare")
    private ReconciliationResponse compareTransaction(@RequestParam(value = "clientReport") MultipartFile clientReport,
                                                      @RequestParam(value = "orgReport") MultipartFile orgReport) {

        return reconciliationService.getTrxComparisonResponse(clientReport, orgReport);
    }
}
