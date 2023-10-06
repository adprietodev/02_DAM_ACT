import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { View, Button, StyleSheet, Dimensions } from "react-native";

export default function App() {
  const [colorBg, setColorBg] = useState("green");
  const [colorBt, setColorBt] = useState("yellow");
  const [sizeW, setSizeW] = useState(200);

  function handleOnPress() {
    if (colorBg == "green") {
      setColorBg("yellow");
      setColorBt("green");
      if (sizeW != 350) setSizeW(sizeW + 50);
    } else {
      setColorBg("green");
      setColorBt("yellow");
      if (sizeW != 350) setSizeW(sizeW + 50);
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
