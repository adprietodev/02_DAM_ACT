import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import axios from "axios";
import { StyleSheet, Text, View, TextInput, Button, Image } from "react-native";

export default function App() {
  const [urls, setData] = useState({ url1: "", url2: "" });

  const getData = async () => {
    try {
      const response1 = await axios.get(
        "https://api.thecatapi.com/v1/images/search?size=full"
      );

      const response2 = await axios.get(
        "https://api.thecatapi.com/v1/images/search?size=full"
      );
      setData({ url1: response1.data[0].url, url2: response2.data[0].url });
      console.log(typeof urls.url1, typeof urls.url2);
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <View style={styles.container}>
      {urls.url1 && (
        <Image
          style={{ width: 200, height: 200 }}
          source={{ uri: urls.url1 }}
        />
      )}
      {urls.url2 && (
        <Image
          style={{ width: 200, height: 200 }}
          source={{ uri: urls.url2 }}
        />
      )}
      <Button onPress={getData} title="¡Pulsame!" />
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
