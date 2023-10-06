import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { View, Text, StyleSheet, TouchableOpacity, Image } from "react-native";

export default function App() {
  const [imgZero, setImgZero] = useState(require("./assets/zoro.jpg"));
  const [imgOne, setImgOne] = useState(require("./assets/denji.jpg"));
  const [textZero, setTextZero] = useState("Text 1");
  const [textOne, setTextOne] = useState("Text 2");

  function handleOnPress(num) {
    if (num == 0) {
      if (textZero == "Text 1") {
        setImgZero(require("./assets/denji.jpg"));
        setTextZero("Text 1 cambiado");
      } else {
        setImgZero(require("./assets/zoro.jpg"));
        setTextZero("Text 1");
      }
    }

    if (num == 1) {
      if (textOne == "Text 2") {
        setImgOne(require("./assets/zoro.jpg"));
        setTextOne("Text 2 cambiado");
      } else {
        setImgOne(require("./assets/denji.jpg"));
        setTextOne("Text 2");
      }
    }
  }
  return (
    <View style={[styles.container]}>
      <TouchableOpacity onPress={() => handleOnPress(0)}>
        <Image style={styles.image} source={imgZero} />
        <Text>{textZero}</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => handleOnPress(1)}>
        <Image style={styles.image} source={imgOne} />
        <Text>{textOne}</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row",
  },
  image: {
    width: 100,
    height: 100,
  },
});
