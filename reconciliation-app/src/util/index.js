import { toast } from "react-toastify";

export const showErrorMessagesInToaster = (message) => {
  toast.error(message, {
    position: "top-right",
    theme: "colored",
    autoClose: 5000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    hideProgressBar: true,
  });
};
