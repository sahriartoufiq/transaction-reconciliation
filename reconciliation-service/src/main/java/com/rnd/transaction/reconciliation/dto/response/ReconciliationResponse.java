package com.rnd.transaction.reconciliation.dto.response;

import com.rnd.transaction.reconciliation.dto.TrxComparisonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReconciliationResponse {

    private TrxComparisonDTO clientResult;
    private TrxComparisonDTO orgResult;
}
