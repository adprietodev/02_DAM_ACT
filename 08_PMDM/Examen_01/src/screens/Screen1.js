//Adrián Prieto Villena
import { StatusBar } from "expo-status-bar";
import { Button } from "react-native";
import { StyleSheet, Text, View } from "react-native";
import { useState } from "react";

export default function Screen1() {
  //Api --> https://rickandmortyapi.com/api/episode?page=1

  const [dataPage, setDataPage] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);

  const getDataRaM = async () => {
    try {
      console.log(
        `https://rickandmortyapi.com/api/episode?page=${currentPage}`
      );
      const response = await fetch(
        `https://rickandmortyapi.com/api/episode?page=${currentPage}`
      );
      if (response.ok) {
        const jsonResponse = await response.json();
        let auxArr = [];
        auxArr = [...jsonResponse.results];
        setDataPage(auxArr);
        //console.log(jsonResponse);
        return jsonResponse;
      }
      throw new Error("La petición a fallado");
    } catch (error) {
      console.log(error);
    }
  };

  const changePage = (page) => {
    console.log(page);
    if (page === "next") {
      console.log("Entramos a " + page);
      if (currentPage + 1 === 4) {
        setCurrentPage(1);
      } else {
        setCurrentPage(currentPage + 1);
      }
    }
    if (page === "prev") {
      console.log("Entramos a " + page);
      if (currentPage - 1 === 0) {
        setCurrentPage(3);
      } else {
        setCurrentPage(currentPage - 1);
      }
    }

    getDataRaM();
  };

  return (
    <View>
      <Button title="Rick and Morty" onPress={getDataRaM} />
      {dataPage &&
        dataPage.map((data, index) => (
          <Text key={index}>{data.episode + " - " + data.name}</Text>
        ))}
      <Button title="Siguiente" onPress={() => changePage("next")} />
      <Button title="Anterior" onPress={() => changePage("prev")} />
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
