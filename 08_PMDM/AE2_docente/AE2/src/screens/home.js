import { StatusBar } from "expo-status-bar";
import { Button } from "react-native";
import { StyleSheet, Text, View } from "react-native";

export default function Home(props) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Word Games</Text>
      <Button
        title="go to crosswords"
        onPress={() =>
          props.navigation.navigate("DrawerMenu", { screen: "Crosswords" })
        }
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
