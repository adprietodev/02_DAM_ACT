import { TextInput, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext, useEffect, useState } from "react";
import AudioContext from "../components/AudioContext";

const Ej03 = ({ navigation }) => {
  const [nameSound, setNameSound] = useState("vikingSong");
  const [link, setLink] = useState(null);
  const { sound, setSound } = useContext(AudioContext);

  const soundMapping = {
    vikingSong: require("../audio/vikingSong.mp3"),
    EarthMelodies: require("../audio/EarthMelodies.mp3"),
    CBR: require("../audio/CBR.mp3"),
    CHFIL: require("../audio/CHFIL.mp3"),
    Mountains: require("../audio/Mountains.mp3"),
    savage: require("../audio/savage.mp3"),
    valhallaSong: require("../audio/valhallaSong.mp3"),
  };

  useEffect(() => {
    setLink(soundMapping[nameSound]);
  }, [nameSound]);

  async function playLocalSound() {
    const { sound } = await Audio.Sound.createAsync(link);
    setSound(sound);
    await sound.playAsync();
  }

  return (
    <View>
      <TextInput
        value={nameSound}
        onChangeText={(text) => setNameSound(text)}
      />
      <Button
        title="Play"
        onPress={() => {
          playLocalSound();
          navigation.navigate("Playing");
        }}
      />
      <View>
        <Text>Nombre Canciones Disponibles:</Text>
        <Text>vikingSong</Text>
        <Text>EarthMelodies</Text>
        <Text>CBR</Text>
        <Text>CHFIL</Text>
        <Text>Mountains</Text>
        <Text>savage</Text>
        <Text>valhallaSong</Text>
      </View>
    </View>
  );
};

export default Ej03;
