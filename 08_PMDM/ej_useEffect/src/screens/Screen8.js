import { StatusBar } from "expo-status-bar";
import { View, StyleSheet, Button, TextInput, Text } from "react-native";
import { useEffect, useState } from "react";

const Screen8 = () => {
  const [search, setSearch] = useState("");
  const [players, setPlayers] = useState([]);
  const [posArray, setPosArray] = useState(0);
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

  const handlePess = (pos) => {
    if (pos === "next") {
      if (posArray === players.length - 1) setPosArray(0);
      else setPosArray(posArray + 1);
    }
    if (pos === "prev") {
      if (posArray === 0) setPosArray(players.length - 1);
      else setPosArray(posArray - 1);
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
      <Text>Cantidad total: {players.length}</Text>
      {players[posArray] && (
        <View>
          <Text>
            Nombre:
            {players[posArray].first_name + " " + players[posArray].last_name}
          </Text>
          <Text>Equipo: {players[posArray].team.full_name}</Text>
          <Text>Posición: {players[posArray].position}</Text>
        </View>
      )}
      <Button title="Siguiente" onPress={() => handlePess("next")} />
      <Button title="Anterior" onPress={() => handlePess("prev")} />
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

export default Screen8;
