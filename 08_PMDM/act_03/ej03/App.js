import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, Button, TextInput } from "react-native";

export default function App() {
  const [text, setText] = useState("");
  const [num, setNum] = useState("");

  function handleOnPress() {
    setNum(1.0546 * text + "$");
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Convertidor de € - $</Text>
      <TextInput
        style={{ height: 60 }}
        placeholder="Inserta el texto..."
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Text style={styles.result}>{num}</Text>
      <Button title="Convertir" onPress={handleOnPress} />
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
