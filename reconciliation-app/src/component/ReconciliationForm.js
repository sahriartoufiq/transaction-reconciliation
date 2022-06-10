import React, { useState } from "react";
import { useForm } from "react-hook-form";
import styled from "styled-components";
import { apiBaseUrl } from "../util/env";
import { showErrorMessagesInToaster } from "../util";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const FormWrapper = styled.fieldset`
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  justify-content: center;
  align-items: center;
  margin: 24px auto;
  width: 60%;
`;

const InputWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  min-width: 400px;
  height: 48px;
`;

const SubmitButton = styled.input`
  width: 180px;
  height: 40px;
  color: blue;
  margin: 0px auto;
`;

function ReconciliationForm(props) {
  const { setResponseData } = props;
  const orgFile = React.createRef();
  const clientFile = React.createRef();
  const [loading, setLoading] = useState(false);

  const { handleSubmit } = useForm({
    mode: "onBlur",
  });

  const getFormData = (data) => {
    const formData = new FormData();

    for (let key in data) {
      formData.append(key, data[key]);
    }

    formData.append(
      "orgReport",
      orgFile.current.files[0],
      orgFile.current.files[0].name
    );

    formData.append(
      "clientReport",
      clientFile.current.files[0],
      clientFile.current.files[0].name
    );

    return formData;
  };

  const onSubmitFn = (data) => {
    setLoading(true);
    setResponseData(null);
    fetch(apiBaseUrl + "/api/transactions/compare", {
      method: "post",
      body: getFormData(data),
    })
      .then((response) => {
        setLoading(false);
        const data = response.json();
        if (response.ok) {
          return data;
        }

        showErrorMessagesInToaster(
          (data && data.error) || "An error occurred!"
        );
      })
      .then((response) => {
        setResponseData(response);
      });
  };
  return (
    <>
      <FormWrapper>
        <legend align="left">Select files to compare</legend>
        <form onSubmit={handleSubmit(onSubmitFn)}>
          <InputWrapper>
            <label>Organization file:</label>
            <input
              type="file"
              name="orgFile"
              ref={orgFile}
              accept=".csv"
              required
            />
          </InputWrapper>
          <InputWrapper>
            <label>Client file:</label>
            <input
              type="file"
              name="clientFile"
              ref={clientFile}
              accept=".csv"
              required
            />
          </InputWrapper>
          <SubmitButton
            type="submit"
            className="submit-button"
            value={loading ? "Loading" : "Compare"}
          />
        </form>
      </FormWrapper>
      <ToastContainer />
    </>
  );
}

export default ReconciliationForm;
