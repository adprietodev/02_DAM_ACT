import { ScrollView, StyleSheet, Text, View, Image } from "react-native";

export default function Article() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Figura Roronoa Zoro</Text>
      <Image style={styles.img} source={require("../assets/favicon.png")} />
      <Text>
        Se trata de una figura con muy buena calidad del vicecapitan de los
        mugiwaras.
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#b0e0e6",
    alignItems: "center",
    justifyContent: "center",
  },
  title: {
    fontSize: 12,
    fontWeight: "bold",
    textDecorationLine: "underline",
  },
  img: {
    width: 80,
    height: 80,
  },
  footer: {
    flex: 1,
    backgroundColor: "blue",
    maxHeight: 15,
  },
});
