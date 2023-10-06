import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { View, Button, StyleSheet, Dimensions } from "react-native";

export default function App() {
  const [colorBg, setColorBg] = useState("green");
  const [colorBt, setColorBt] = useState("yellow");
  const [sizeW, setSizeW] = useState(200);
  const [change, setChange] = useState(true);

  function handleOnPress() {
    if (colorBg == "white") {
      setColorBg("black");
      setColorBt("green");
    } else {
      setColorBg("white");
      setColorBt("yellow");
    }

    if (change == false) {
      setSizeW(sizeW - 50);
      if (sizeW < 100) {
        setChange(true);
      }
    } else {
      setSizeW(sizeW + 50);
      if (sizeW > 350) {
        setChange(false);
      }
    }
  }

  return (
    <View style={[styles.container, { backgroundColor: colorBg }]}>
      <View
        style={[styles.square, { width: sizeW, backgroundColor: colorBt }]}
      />
      <Button title="Pulsame!" onPress={handleOnPress} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "green",
    alignItems: "center",
    justifyContent: "center",
  },
  square: {
    size: "100",
    mt: "-2",
    marginTop: -6,
    width: 200,
    height: 200,
    backgroundColor: "yellow",
  },
});
