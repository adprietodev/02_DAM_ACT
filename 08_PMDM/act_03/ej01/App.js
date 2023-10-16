import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, Button, TextInput } from "react-native";

export default function App() {
  const [text, setText] = useState("");

  function handleOnPress() {
    if (text === "") {
      alert("No has escrito nada");
    } else if (!isNaN(text)) {
      alert("Lo escrito en el input es un numero ");
    } else {
      alert("Lo escrito en el input es un texto");
    }
  }

  return (
    <View style={styles.container}>
      <TextInput
        style={{ height: 60 }}
        placeholder="Inserta el texto..."
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Button title="Coger tipo" onPress={handleOnPress} />
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
