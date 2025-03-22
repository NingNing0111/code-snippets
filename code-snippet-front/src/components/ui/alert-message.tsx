import { Alert, AlertTitle } from "./alert";
import { useEffect, useState } from "react";

interface Props {
  showSavedAlert: boolean;
  message: string;
  timeout: number; // in seconds
}

const AlertMessage = (props: Props) => {
  const [isVisible, setIsVisible] = useState(props.showSavedAlert);

  useEffect(() => {
    setIsVisible(props.showSavedAlert);
    if (props.showSavedAlert) {
      const timer = setTimeout(() => {
        setIsVisible(false);
      }, props.timeout * 1000);

      return () => clearTimeout(timer);
    }
  }, [props.showSavedAlert, props.timeout]);

  return (
    <>
      {isVisible && (
        <div className="fixed top-4 left-1/2 -translate-x-1/2 z-50">
          <Alert variant="default">
            <AlertTitle className="text-green-600">{props.message}</AlertTitle>
          </Alert>
        </div>
      )}
    </>
  );
};

export default AlertMessage;
