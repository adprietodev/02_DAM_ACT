import { StatusBar } from "expo-status-bar";
import {
  View,
  StyleSheet,
  Button,
  TextInput,
  Text,
  ScrollView,
} from "react-native";
import { useEffect, useState } from "react";

const Screen7 = () => {
  const [search, setSearch] = useState("");
  const [players, setPlayers] = useState([]);
  useEffect(() => {
    getData();
  }, []);

  const getData = async () => {
    try {
      const response = await fetch(
        `https://www.balldontlie.io/api/v1/players?search=${search}`
      );

      if (response.ok) {
        const res = await response.json();
        const newArray = [...res.data];
        setPlayers(newArray);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder={"Buscador..."}
        onChangeText={(text) => setSearch(text)}
      />
      <Button title="BUSCAR" onPress={getData} />
      <ScrollView style={{ width: "auto", height: "auto" }}>
        {players.map((player) => (
          <View>
            <Text>Nombre: {player.first_name + " " + player.last_name}</Text>
            <Text>Equipo: {player.team.full_name}</Text>
            <Text>Posición: {player.position}</Text>
          </View>
        ))}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "#ecf0f1",
    padding: 8,
  },
  img: {
    width: "auto",
    height: 300,
  },
  input: {
    width: "auto",
    height: 50,
    backgroundColor: "white",
    borderRadius: 16,
    borderColor: "black",
    borderWidth: 2,
    marginBottom: 8,
    padding: 16,
  },
});

export default Screen7;
