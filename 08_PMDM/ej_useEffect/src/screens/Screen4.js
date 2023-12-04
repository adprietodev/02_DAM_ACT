import { StatusBar } from "expo-status-bar";
import { Image, View, StyleSheet, Button, Text } from "react-native";
import { useEffect, useState } from "react";

const Screen4 = () => {
  const [character, setCharacter] = useState({});
  useEffect(() => {
    getData();
  }, []);

  const getData = async () => {
    try {
      const response = await fetch("https://rickandmortyapi.com/api/character");
      if (response.ok) {
        const res = await response.json();
        setCharacter(res.results[0]);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <Text>Nombre: {character.name}</Text>
      <Text>Especie: {character.species}</Text>
      <Text>Estado: {character.status}</Text>
      <Image style={styles.img} source={{ uri: character.image }} />
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
});

export default Screen4;
