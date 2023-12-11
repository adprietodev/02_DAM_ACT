import { StatusBar } from "expo-status-bar";
import { View, StyleSheet, Text, TextInput, Button } from "react-native";
import { useContext, useState } from "react";
import ScreensContext from "./ScreensContext";

const Screen1 = ({ navigation }) => {
  const [word, setWord] = useState();
  const { name, setName } = useContext(ScreensContext);
  const onSubmit = () => {
    setName(word);
    navigation.navigate("Screen2");
  };
  return (
    <View>
      <TextInput
        placeholder="Introduce la plabra..."
        onChangeText={(text) => setWord(text)}
        value={word}
      />
      <Button title="Buscar" onPress={onSubmit} />
    </View>
  );
};

export default Screen1;
