import { StatusBar } from "expo-status-bar";
import { Image, View, StyleSheet, Button, Text } from "react-native";
import { useEffect, useState } from "react";

const Screen6 = () => {
  const [characters, setCharacter] = useState([]);
  const [posArray, setPosArray] = useState(0);
  useEffect(() => {
    getData();
    //console.log(characters.length);
  }, []);

  const getData = async () => {
    try {
      const response = await fetch("https://rickandmortyapi.com/api/character");
      if (response.ok) {
        const res = await response.json();
        const newArray = [...res.results];
        setCharacter(newArray);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handlePess = (pos) => {
    if (pos === "next") {
      if (posArray === characters.length - 1) setPosArray(0);
      else setPosArray(posArray + 1);
    }
    if (pos === "prev") {
      if (posArray === 0) setPosArray(characters.length - 1);
      else setPosArray(posArray - 1);
    }
  };

  return (
    <View style={styles.container}>
      {characters[posArray] && <Text>{characters[posArray].name}</Text>}
      {characters[posArray] && <Text>{characters[posArray].species}</Text>}
      {characters[posArray] && <Text>{characters[posArray].status}</Text>}
      {characters[posArray] && (
        <Image
          style={styles.img}
          source={{ uri: characters[posArray].image }}
        />
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
});

export default Screen6;
