import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { StyleSheet, Text, View, Button, Image } from "react-native";

export default function App() {
  const [color, setColor] = useState("green");
  const [text, setText] = useState("My Title");

  function handleOnPress() {
    if (color == "green") {
      setColor("yellow");
      setText("My New Title");
    } else {
      setColor("green");
      setText("My Title");
    }
  }

  return (
    <View style={[styles.container, { backgroundColor: color }]}>
      <Text style={styles.title}>{text}</Text>
      <Image style={styles.image} source={require("./assets/snack-icon.png")} />
      <Button title="Pulsame!" onPress={handleOnPress} />
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
    fontSize: 25,
    fontWeight: "bold",
    fontStyle: "italic",
    textDecorationLine: "underline",
  },
  image: {
    width: 200,
    height: 200,
  },
});
