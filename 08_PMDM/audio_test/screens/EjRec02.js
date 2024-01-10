import { TextInput, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext, useState, useEffect } from "react";
import AudioContext from "../components/AudioContext";

const EjRec01 = ({ navigation }) => {
  const { sound, setSound } = useContext(AudioContext);
  const [recording, setRecording] = useState(null);
  const [URI, setURI] = useState(null);
  const [btnName, setBtnName] = useState("START RECORDING");

  const [saveURIS, setSaveURIS] = useState([]);
  const [currentPos, setCurrentPos] = useState(0);

  const [changesCompleted, setChangesCompleted] = useState(false);

  //Empezar a grabar audio
  const startRecording = async () => {
    try {
      await Audio.requestPermissionsAsync();
      await Audio.setAudioModeAsync({
        allowsRecordingIOS: true,
        playsInSilentModeIOS: true,
      });
      const { recording } = await Audio.Recording.createAsync(
        Audio.RECORDING_OPTIONS_PRESET_HIGH_QUALITY
      );
      setRecording(recording);
    } catch (error) {
      console.log(error);
    }
  };

  //Dejar de grabar audio

  const stopRecording = async () => {
    await recording.stopAndUnloadAsync();
    setURI(recording.getURI());
  };

  async function playRecording() {
    try {
      if (URI) {
        const { sound } = await Audio.Sound.createAsync({ uri: URI });
        setSound(sound);
        await sound.playAsync();
      } else {
        console.error("No hay URI disponible para reproducir.");
      }
    } catch (error) {
      console.error("Error al reproducir la grabación:", error);
    }
  }

  const saveRecording = () => {
    let newArray = [...saveURIS];
    newArray.push(URI);
    setSaveURIS(newArray);
    setCurrentPos(newArray.length);
  };

  useEffect(() => {
    if (changesCompleted) {
      playRecording();
      setChangesCompleted(false);
    }
  }, [changesCompleted]);

  const changeAudio = (op) => {
    if (op === "next") {
      if (saveURIS.length === currentPos) {
        setURI(saveURIS[0]);
        setCurrentPos(0);
      } else {
        setURI(saveURIS[currentPos + 1]);
        setCurrentPos(currentPos + 1);
      }
    }
    if (op === "prev") {
      if (currentPos === 0) {
        setURI(saveURIS[saveURIS.length - 1]);
        setCurrentPos(saveURIS.length - 1);
      } else {
        setURI(saveURIS[currentPos - 1]);
        setCurrentPos(currentPos - 1);
      }
    }

    setChangesCompleted(true);
  };

  return (
    <View>
      <Button
        title={btnName}
        onPress={() => {
          if (btnName === "STOP RECORDING") {
            stopRecording();
            setBtnName("START RECORDING");
          } else {
            startRecording();
            setBtnName("STOP RECORDING");
          }
        }}
      />
      <Button title="PLAY RECORDING" onPress={() => playRecording()} />
      <Button title="SAVE RECORDING" onPress={() => saveRecording()} />
      <Button title="NEXT AUDIO" onPress={() => changeAudio("next")} />
      <Button title="PREVIOUS AUDIO" onPress={() => changeAudio("prev")} />
    </View>
  );
};

export default EjRec01;
