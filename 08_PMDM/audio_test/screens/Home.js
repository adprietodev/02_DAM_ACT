import { StyleSheet, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext } from "react";
import AudioContext from "../components/AudioContext";

const Home = ({ navigation }) => {
  const { sound, setSound } = useContext(AudioContext);

  async function playLocalSound() {
    const { sound } = await Audio.Sound.createAsync(
      require("../audio/vikingSong.mp3")
    );
    setSound(sound);
    await sound.playAsync();
  }
  return (
    <View>
      <Button
        title="Ejercicio 01"
        onPress={() => {
          playLocalSound();
          navigation.navigate("Ej01");
        }}
      />
      <Button
        title="Ejercicio 03"
        onPress={() => {
          navigation.navigate("Ej03");
        }}
      />
      <Button
        title="Ejercicio 04"
        onPress={() => {
          navigation.navigate("Ej04");
        }}
      />
      <Button
        title="Grabación Ejercicio 01"
        onPress={() => {
          navigation.navigate("EjRec01");
        }}
      />
      <Button
        title="Grabación Ejercicio 02"
        onPress={() => {
          navigation.navigate("EjRec02");
        }}
      />
    </View>
  );
};

export default Home;
