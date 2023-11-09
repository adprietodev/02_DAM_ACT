import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, TextInput, Button } from "react-native";

export default function App() {
  const [number, setNumber] = useState("");
  const [numSave, setNumSave] = useState([]);
  const [acumSave, setAcSave] = useState([]);

  const handlerEvent = () => {
    if (isNaN(number) || number === "") {
      alert(
        "No has insertado un número válido, por favor, inserta un número válido."
      );
      setNumber("");
    } else {
      let newArr = [...numSave];
      let acArr = [...acumSave];
      newArr.push(number);
      let num = add(...newArr);
      acArr.push(num);
      setNumSave(newArr);
      setAcSave(acArr);
      setNumber("");
    }
  };

  const add = (...params) => {
    let res = "";
    for (let value of params) {
      res += "" + value;
    }
    return res;
  };

  return (
    <View style={styles.container}>
      <TextInput
        placeholder="Inserta tu número..."
        onChangeText={(number) => setNumber(number)}
        value={number}
      />
      <Button title="Añadir" onPress={handlerEvent} />
      {acumSave.map((value, index) => {
        return <Text key={index}>{value}</Text>;
      })}
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
