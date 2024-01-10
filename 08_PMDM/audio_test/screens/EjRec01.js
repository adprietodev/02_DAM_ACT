import { TextInput, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext, useState } from "react";
import AudioContext from "../components/AudioContext";

const EjRec01 = ({ navigation }) => {
  const { sound, setSound } = useContext(AudioContext);
  const [recording, setRecording] = useState(null);
  const [URI, setURI] = useState(null);
  const [btnName, setBtnName] = useState("START RECORDING");

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
    </View>
  );
};

export default EjRec01;
