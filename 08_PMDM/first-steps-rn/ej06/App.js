import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  let title = "First Steps in React Nactive";
  return (
    <View style={styles.container}>
      <Text>{title}</Text>
      <Text>
        Cambiamos el texto como solicita en el ejercicio 1 de los primeros pasos
        en react native
      </Text>
      <Text>
        En este puto estamos en el ejercicio 4 donde dejamos vacío el padre
      </Text>
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
  footer: {
    flex: 1,
    backgroundColor: "blue",
    maxHeight: 15,
  },
});
