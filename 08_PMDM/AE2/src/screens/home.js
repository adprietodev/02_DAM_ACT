import { StatusBar } from "expo-status-bar";
import { Button } from "react-native";
import { StyleSheet, Text, View } from "react-native";

export default function Home(props) {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app! - Home</Text>
      <Button
        title="crosswords"
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
});
