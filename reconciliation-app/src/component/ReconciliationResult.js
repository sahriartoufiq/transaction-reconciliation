import React, { useState } from "react";
import styled from "styled-components";
import UnmatchedReport from "./UnmatchedReport";
import SummaryReport from "./SummaryReport";

const ResultWrapper = styled.div`
  display: flex;
  width: 100%;
  margin: auto;
`;

const SummaryFieldset = styled.fieldset`
  margin: 200 200px;
  display: flex;
  margin: auto;
  flex-direction: column;
  align-items: center;
  padding: 16px;
`;

const Button = styled.button`
  width: 240px;
  height: 40px;
  color: blue;
  margin-top: 16px;
`;

const ReportFieldset = styled.fieldset`
  display: flex;
  margin: 32px auto;
`;

function ReconciliationResult(props) {
  const { clientResult, orgResult } = props.data || {};
  const [showUnmatchedReport, setShowUnmatchedReport] = useState(false);

  return (
    <>
      {orgResult && clientResult && (
        <>
          <SummaryFieldset>
            <legend align="left">Comparison result</legend>
            <ResultWrapper>
              <SummaryReport
                title="Organizational report"
                summaryData={orgResult}
              />
              <SummaryReport title="Client report" summaryData={clientResult} />
            </ResultWrapper>
            <Button
              onClick={() => setShowUnmatchedReport(!showUnmatchedReport)}
            >
              {showUnmatchedReport
                ? "Hide unmatched report"
                : "Show unmatched report"}
            </Button>
          </SummaryFieldset>
          {showUnmatchedReport && (
            <ReportFieldset>
              <legend align="left">Unmatched report</legend>
              <UnmatchedReport
                title="Organization unmatched report"
                data={orgResult.unmatchedRecordList}
              />
              <UnmatchedReport
                title="Client unmatched report"
                data={clientResult.unmatchedRecordList}
              />
            </ReportFieldset>
          )}
        </>
      )}
    </>
  );
}

export default ReconciliationResult;
