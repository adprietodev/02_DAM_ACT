import { StyleSheet, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext, useEffect } from "react";
import AudioContext from "../components/AudioContext";

const Playing = ({ navigation }) => {
  //Para poder pausar el audio hay que crear un useState para guardar el audio.
  const { sound, setSound } = useContext(AudioContext);
  useEffect(() => {
    return sound
      ? () => {
          sound.unloadAsync();
        }
      : undefined;
  }, [sound]);
  return (
    <View>
      <Text>Reproduciendo audio</Text>
    </View>
  );
};

export default Playing;
