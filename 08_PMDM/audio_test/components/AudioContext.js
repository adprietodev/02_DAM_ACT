import { createContext, useState } from "react";
import { Audio } from "expo-av";

const AudioContext = createContext();

export const AudioProvider = ({ children }) => {
  const [sound, setSound] = useState(null);

  return (
    <AudioContext.Provider value={{ sound, setSound }}>
      {children}
    </AudioContext.Provider>
  );
};

export default AudioContext;
