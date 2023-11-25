//Adrián Prieto Villena
import { StatusBar } from "expo-status-bar";
import { Button } from "react-native";
import { StyleSheet, Text, View } from "react-native";

export default function Home(props) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Word Games</Text>
      <Button
        title="Screen 1"
        onPress={() => props.navigation.navigate("Screen1")}
      />
      <Button
        title="Screen 2"
        onPress={() => props.navigation.navigate("Screen2")}
      />
      <StatusBar style="auto" />
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
    margin: 24,
    fontSize: 18,
    fontWeight: "bold",
  },
});
