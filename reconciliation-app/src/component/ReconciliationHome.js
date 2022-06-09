import React, { useState } from "react";
import styled from "styled-components";
import ReconciliationResult from "./ReconciliationResult";
import ReconciliationForm from "./ReconciliationForm";

const Container = styled.div`
  margin: auto;
  width: 90%;
`;

const Header = styled.div`
  margin-top: 32px;
  margin-bottom: 16px
  text-align: center;
  font-size: xx-large;
`;

function ReconciliationHome() {
  const [data, setData] = useState(null);

  return (
    <Container>
      <Header>Transaction Reconciliation</Header>
      <ReconciliationForm setResponseData={(res) => setData(res)} />
      <ReconciliationResult data={data} />
    </Container>
  );
}

export default ReconciliationHome;
