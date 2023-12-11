import { createContext, useState } from "react";

const ScreensContext = createContext();

export const ScreensProvider = ({ children }) => {
  const [name, setName] = useState();

  return (
    <ScreensContext.Provider value={{ name, setName }}>
      {children}
    </ScreensContext.Provider>
  );
};
export default ScreensContext;
