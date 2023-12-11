import { StatusBar } from "expo-status-bar";
import { View, StyleSheet, Text } from "react-native";
import { useContext, useEffect, useState } from "react";
import ScreensContext from "./ScreensContext";

const Screen2 = ({ navigation }) => {
  const [definition, setDefinition] = useState();
  const { name, setName } = useContext(ScreensContext);

  useEffect(() => {
    getData();
  }, []);

  const getData = async () => {
    try {
      const response = await fetch(
        `https://api.dictionaryapi.dev/api/v2/entries/en/${name}`
      );

      if (response.ok) {
        const res = await response.json();
        setDefinition(res[0].meanings[0].definitions[0].definition);
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <View>
      <Text>{definition}</Text>
    </View>
  );
};

export default Screen2;
