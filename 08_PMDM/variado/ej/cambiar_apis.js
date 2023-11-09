import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import axios from "axios";
import { StyleSheet, Text, View, TextInput, Button, Image } from "react-native";

export default function App() {
  const [searchTerm, setSearchTerm] = useState("");
  const [info, setInfo] = useState({});
  const [numSearch, setNumSearch] = useState(0);

  const getData = async () => {
    try {
      const response = await axios.get(
        `https://api.github.com/search/users?q=${searchTerm}`
      );
      setInfo(response.data.items[numSearch]);
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <View style={styles.container}>
      {info && (
        <Image
          style={{ width: 200, height: 200 }}
          source={{ uri: info.avatar_url }}
        />
      )}
      <TextInput
        placeholder="Inserta lo que quieres buscar..."
        onChangeText={(searchTerm) => setSearchTerm(searchTerm)}
      />
      {info && <Text>{info.id}</Text>}
      <Button onPress={getData} title="¡Buscar Nombre!" />
      <TextInput
        placeholder="Inserta el posicion buscar..."
        onChangeText={(numSearch) => setSearchTerm(numSearch)}
        value={numSearch}
      />
      <Button onPress={getData} title="¡Buscar ID!" />
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
