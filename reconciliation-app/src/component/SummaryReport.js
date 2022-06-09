import styled from "styled-components";
import React from "react";

const Header = styled.div`
  font-size: x-large;
  margin-bottom: 10px;
  border-bottom: 2px solid;
`;

const SummaryReportWrapper = styled.div`
  padding: 1.25rem 1.25rem;
  flex-grow: 0;
  text-align: center;
  flex-direction: column;
  display: flex;
  flex-grow: 1;
  justify-content: space-evenly;
  align-items: center;
`;

const TextWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 200px;
  height: 32px;
`;

function SummaryReport(props) {
  const { title, summaryData } = props || {};

  return (
    <SummaryReportWrapper key={title}>
      <Header>{title}</Header>
      <TextWrapper>
        <p>Total Record:</p>
        <p>{summaryData.totalRecords}</p>
      </TextWrapper>
      <TextWrapper>
        <p>Matching Record:</p>
        <p>{summaryData.matchingRecords}</p>
      </TextWrapper>
      <TextWrapper>
        <p>Unmatched Record:</p>
        <p>{summaryData.unmatchedRecords}</p>
      </TextWrapper>
    </SummaryReportWrapper>
  );
}

export default SummaryReport;
