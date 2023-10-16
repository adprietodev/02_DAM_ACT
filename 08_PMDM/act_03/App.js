import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, Button, TextInput } from "react-native";

export default function App() {
  const [text, setText] = useState("");
  let arr = text.split("");

  function handleOnPress() {
    console.log(validateArroba() + " " + validateCom());
    if (validateArroba() && validateCom()) {
      alert("Correo electronico válido.");
    }
    if (!validateArroba() || !validateCom()) {
      alert(
        "Correo electronico no es válido. Recuerda que debe estar compuesto por xxxxxx@xxxx.com o xxxxxx@xxxx.es"
      );
    }
  }

  function validateArroba() {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] === "@") {
        return true;
        break;
      }
    }

    return false;
  }

  function validateCom() {
    let com = [];
    let es = [];
    let j = 0;
    for (let i = arr.length - 4; i < arr.length; i++) {
      com[j] = arr[i];
      j++;
    }
    j = 0;

    for (let i = arr.length - 3; i < arr.length; i++) {
      es[j] = arr[i];
      j++;
    }
    console.log(es.join(""));
    if (com.join("") === ".com" || es.join("") === ".es") {
      return true;
    } else {
      return false;
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Validador de email</Text>
      <TextInput
        style={{ height: 60 }}
        placeholder="Inserta el texto..."
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Button title="Validar" onPress={handleOnPress} />
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
    fontSize: 32,
  },
  result: {
    fontSize: 28,
    fontWeight: "bold",
  },
});
