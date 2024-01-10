import { createContext, useState } from "react";
import { Audio } from "expo-av";

const RecordingContext = createContext();

export const RecordingProvider = ({ children }) => {
  const [recording, setRecording] = useState(null);

  return (
    <RecordingContext.Provider value={{ recording, setSound }}>
      {children}
    </RecordingContext.Provider>
  );
};

export default RecordingContext;
