import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  let title = "First Steps in React Nactive";
  let content = {
    paragraphOne: "Primer cadena de texto",
    paragraphTwo: "Segunda cadena de texto",
    title2: "Esta es la primera app",
  };
  return (
    <View style={styles.container}>
      <Text style={styles.title}>{content.title2}</Text>
      <Text>{content.paragraphOne}</Text>
      <Text>{content.paragraphTwo}</Text>
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
  title: {
    fontSize: 12,
    fontWeight: "bold",
    textDecorationLine: "underline",
  },
  footer: {
    flex: 1,
    backgroundColor: "blue",
    maxHeight: 15,
  },
});
