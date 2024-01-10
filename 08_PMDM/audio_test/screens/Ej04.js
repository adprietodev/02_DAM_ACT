import { TextInput, Text, View, Button } from "react-native";
import { Audio } from "expo-av";
import { useContext, useEffect, useState } from "react";
import { SelectList } from "react-native-dropdown-select-list";
import AudioContext from "../components/AudioContext";

const Ej04 = ({ navigation }) => {
  const [link, setLink] = useState(null);
  const { sound, setSound } = useContext(AudioContext);
  const [selected, setSeleted] = useState("");

  const dataName = [
    { key: "1", value: "vikingSong" },
    { key: "2", value: "EarthMelodies" },
    { key: "3", value: "CBR" },
    { key: "4", value: "CHFIL" },
    { key: "5", value: "Mountains" },
    { key: "6", value: "savage" },
    { key: "7", value: "valhallaSong" },
  ];

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
    setLink(soundMapping[selected]);
  }, [selected]);

  async function playLocalSound() {
    const { sound } = await Audio.Sound.createAsync(link);
    setSound(sound);
    await sound.playAsync();
  }

  return (
    <View>
      <SelectList
        setSelected={(val) => setSeleted(val)}
        data={dataName}
        save="value"
        label="Canciones:"
      />
      <Button
        title="Play"
        onPress={() => {
          playLocalSound();
          navigation.navigate("Playing");
        }}
      />
    </View>
  );
};

export default Ej04;
