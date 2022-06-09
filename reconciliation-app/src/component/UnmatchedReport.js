import React from "react";
import DataTable from "react-data-table-component";
import styled from "styled-components";

const TableWrapper = styled.div`
  width: 45%;
  margin: 16px 16px;
`;

const columns = [
  {
    id: 1,
    name: "trx id",
    selector: (row) => row.transactionId,
    wrap: true,
  },
  {
    id: 2,
    name: "description",
    selector: (row) => row.transactionDescription,
    wrap: true,
  },
  {
    id: 3,
    name: "date",
    selector: (row) => row.transactionDate,
    wrap: true,
  },
  {
    id: 4,
    name: "reference",
    selector: (row) => row.walletReference,
    wrap: true,
  },
  {
    id: 5,
    name: "amount",
    selector: (row) => row.transactionAmount,
    wrap: true,
  },
  {
    id: 6,
    name: "probable match trx id",
    selector: (row) => row.probableMatchTrxId,
    wrap: true,
  },
];

function UnmatchedReport(props) {
  const { data, title } = props;

  return (
    <TableWrapper>
      <div key={title}>
        <DataTable title={title} columns={columns} data={data} pagination />
      </div>
    </TableWrapper>
  );
}

export default UnmatchedReport;
