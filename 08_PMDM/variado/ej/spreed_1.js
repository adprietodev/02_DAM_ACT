import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, TextInput, Button } from "react-native";

export default function App() {
  const [number, setNumber] = useState("");
  const [numSave, setNumSave] = useState([]);

  const handlerEvent = () => {
    if (isNaN(number)) {
      alert("No has insetado un numero por favor, inserta un numero valido.");
      setNumber("");
    } else {
      let newArr = [...numSave];
      newArr.push(number);
      setNumSave(newArr);
      setNumber("");
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        placeholder="Inserta tu numero..."
        onChangeText={(number) => setNumber(number)}
        defaultValue={number}
      />
      <Button title="Añadir" onPress={handlerEvent} />
      <Text>{numSave}</Text>
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
});
