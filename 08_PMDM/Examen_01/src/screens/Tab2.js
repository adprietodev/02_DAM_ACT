//Adrián Prieto Villena
import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, TextInput, Button } from "react-native";

export default function Screen1() {
  const [inputText, setInputText] = useState("");
  const [result, setResult] = useState("");

  const handlePress = () => {
    let utf16Values = [];
    for (let i = 0; i < inputText.length; i++) {
      utf16Values.push(inputText.charCodeAt(i));
    }
    let numeroEntero = utf16Values.join("");
    numeroEntero = parseInt(numeroEntero, 10);
    let hexadecimalNumber = numeroEntero.toString(16);
    let hexadecimalIbvertido = hexadecimalNumber.split("").reverse().join();
    setResult(hexadecimalIbvertido.replaceAll(",", ""));

    setInputText("");
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={{
          marginVertical: 12,
          height: 40,
          backgroundColor: "blue",
          color: "white",
          textAlign: "center",
        }}
        placeholder="Introduce texto..."
        value={inputText}
        onChangeText={(text) => setInputText(text)}
      />
      <View style={{ width: 100 }}>
        <Button title="Pulsa" onPress={handlePress} />
      </View>

      {inputText !== "" && <Text style={{ marginTop: 20 }}>{inputText}</Text>}
      {result !== "" && <Text style={{ marginTop: 20 }}>{result}</Text>}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  title: {
    margin: 24,
    fontSize: 18,
    fontWeight: "bold",
  },
});
