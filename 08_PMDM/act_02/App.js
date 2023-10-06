import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import { View, StyleSheet, TouchableOpacity } from "react-native";

export default function App() {
  const [color, setColor] = useState("yellow");
  const [sizeW, setSizeW] = useState(200);
  const [sizeH, setSizeH] = useState(200);
  function handleOnPress(pos) {
    if (pos === "sup") {
      setColor("green");
      setSizeH(sizeH + 25);
      setSizeW(sizeH + 25);
      //setSize((parseInt(sizeC) + 25).toString());
    }
    if (pos === "inf") {
      setColor("yellow");
      setSizeH(sizeH - 25);
      setSizeW(sizeH - 25);
      //setSize((parseInt(sizeC) - 25).toString());
    }
  }
  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={[
          styles.square,
          { backgroundColor: color, width: sizeW, height: sizeH },
        ]}
        onPress={() => handleOnPress("sup")}
      />
      <TouchableOpacity
        style={[
          styles.square,
          { backgroundColor: color, width: sizeW, height: sizeH },
        ]}
        onPress={() => handleOnPress("inf")}
      />
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
  square: {
    size: "100",
    mt: "-2",
    marginTop: -6,
    width: 200,
    height: 200,
    backgroundColor: "yellow",
  },
});
