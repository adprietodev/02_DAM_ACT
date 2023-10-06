import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { View, Text, StyleSheet } from "react-native";

export default function App() {
  const [textZero, setTextZero] = useState("Text");
  const [textOne, setTextOne] = useState("Text");
  const [textTwo, setTextTwo] = useState("Text");
  const [textThree, setTextThree] = useState("Text");

  function handleOnPress(num) {
    switch (num) {
      case 0:
        if (textZero === "Text") {
          setTextZero("Cambio de texto");
        } else {
          setTextZero("Text");
        }
        break;
      case 1:
        if (textOne === "Text") {
          setTextOne("Cambio de texto");
        } else {
          setTextOne("Text");
        }
        break;
      case 2:
        if (textTwo === "Text") {
          setTextTwo("Cambio de texto");
        } else {
          setTextTwo("Text");
        }
        break;
      case 3:
        if (textThree === "Text") {
          setTextThree("Cambio de texto");
        } else {
          setTextThree("Text");
        }
        break;
      default:
    }
  }

  return (
    <View style={[styles.container]}>
      <Text style={styles.text} onPress={() => handleOnPress(0)}>
        {textZero}
      </Text>
      <Text style={styles.text} onPress={() => handleOnPress(1)}>
        {textOne}
      </Text>
      <Text style={styles.text} onPress={() => handleOnPress(2)}>
        {textTwo}
      </Text>
      <Text style={styles.text} onPress={() => handleOnPress(3)}>
        {textThree}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    fontSize: 30,
    marginBottom: 20,
  },
});
