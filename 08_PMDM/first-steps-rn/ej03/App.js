import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  return (
    <Text style={styles.container}>
      Cambiamos el texto como solicita en el ejercicio 1 de los primeros pasos
      en react native
    </Text>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  footer: {
    flex: 1,
    backgroundColor: "blue",
    maxHeight: 15,
  },
});
