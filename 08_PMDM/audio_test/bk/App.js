import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, Button } from "react-native";
import { useEffect, useState } from "react";

//Librerias audio
import { Audio } from "expo-av";

//Navigation
import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";

const Stack = createStackNavigator();

const App = () => {
  //Para poder pausar el audio hay que crear un useState para guardar el audio.
  const [sound, setSound] = useState(null);

  useEffect(() => {
    return sound
      ? () => {
          sound.unloadAsync();
        }
      : undefined;
  }, [sound]);

  //Reproducir el audio
  async function playLocalSound() {
    const { sound } = await Audio.Sound.createAsync(require("./audio/sn4.wav"));
    setSound(sound); //Aquí es donde lo guardamos.
    await sound.playAsync();
  }

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
        if (result.isPlaying === true) {
          sound.playAsync();
        }
      }
    } catch (error) {}
  };

  return (
    <View style={styles.container}>
      <Button onPress={() => playLocalSound()} />
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
