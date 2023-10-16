import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, Button, TextInput } from "react-native";

export default function App() {
  const [text, setText] = useState("");
  let arr = text.split("");

  function handleOnPress() {
    console.log(arr);
    if (!validateNum()) {
      alert(
        "El numero no es correcto debe ser de una logitud de 8 mas la letra (12345678A)"
      );
    }
    if (!validateLetter()) {
      alert("El ultimo apartado debe ser una letra");
    }

    if (validateNum() && validateLetter()) {
      alert("DNI Correcto");
    }
  }

  function validateNum() {
    let sum = 0;
    for (let i = 0; i < 8; i++) {
      if (!isNaN(arr[i])) {
        sum++;
      }
    }
    console.log(sum);
    if (sum == 8) {
      return true;
    } else {
      return false;
    }
  }

  let validateLetter = () => isNaN(arr[arr.length - 1]);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Validador de DNI</Text>
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
