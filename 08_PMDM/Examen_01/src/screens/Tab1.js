//Adrián Prieto Villena
import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  ScrollView,
  Button,
} from "react-native";

export default function Screen1() {
  const [word, setWord] = useState("");
  const [definitions, setDefinitions] = useState([]);
  //Api --> https://api.dictionaryapi.dev/api/v2/entries/en/
  //ruta response [0].meanings[0].definitions
  const getDataWord = async () => {
    try {
      const response = await fetch(
        `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
      );
      if (response.ok) {
        const jsonResponse = await response.json();
        let auxArr = [];
        auxArr = [...jsonResponse[0].meanings[0].definitions];
        setDefinitions(auxArr);
        //console.log(jsonResponse);
        return jsonResponse;
      }
      throw new Error("La petición ha fallado");
    } catch (error) {
      setDefinitions(null);
      alert("Has introducido una palabra incorrecta");
      console.log(error);
    }
  };
  return (
    <View>
      <TextInput
        value={word}
        placeholder="Introduce una palabra..."
        onChangeText={(text) => setWord(text)}
      />
      <Button title="Pulsa" onPress={getDataWord} />
      <ScrollView>
        {definitions &&
          definitions.map((def, index) => (
            <Text>{index + "./ " + def.definition}</Text>
          ))}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  title: {
    margin: 24,
    fontSize: 18,
    fontWeight: "bold",
  },
});
