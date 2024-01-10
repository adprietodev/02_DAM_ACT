import { StyleSheet, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext, useEffect } from "react";
import AudioContext from "../components/AudioContext";

const Ej01 = ({ navigation }) => {
  //Para poder pausar el audio hay que crear un useState para guardar el audio.
  const { sound, setSound } = useContext(AudioContext);

  useEffect(() => {
    return sound
      ? () => {
          sound.unloadAsync();
        }
      : undefined;
  }, [sound]);

  //Pausar audio
  const pauseAudio = async () => {
    try {
      const result = await sound.getStatusAsync();
      if (result.isLoaded) {
        if (result.isPlaying === true) {
          sound.pauseAsync();
        }
      }
    } catch (error) {
      console.log(error);
    }
  };

  //Stop Audio
  const stopAudio = async () => {
    await sound.stopAsync();
  };

  //Resume Audio
  const resumeAudio = async () => {
    try {
      const result = await sound.getStatusAsync();
      if (result.isLoaded) {
        if (result.isPlaying === false) {
          sound.playAsync();
        }
      }
    } catch (error) {}
  };

  async function playLocalSound() {
    const { sound } = await Audio.Sound.createAsync(
      require("../audio/vikingSong.mp3")
    );
    setSound(sound);
    await sound.playAsync();
  }

  return (
    <View>
      {/* <Button title="Play" onPress={() => playLocalSound()} /> */}
      <Button title="Pause" onPress={() => pauseAudio()} />
      <Button title="Resume" onPress={() => resumeAudio()} />
      <Button title="Stop" onPress={() => stopAudio()} />
    </View>
  );
};

export default Ej01;
