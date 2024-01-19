import { TouchableOpacity, Text, View, ScrollView } from "react-native";
import { useEffect, useState } from "react";
import definitions from "../../services/data/definitions.json";
import getData from "../../services/Services";
import shuffle from "../../services/Shuffle";
import playAudio from '../../services/PlayAudio';
import getRandomNum from '../../services/RandomNumber';

export default function Definitions() {
  const lvlOne = definitions[0].levelOne;
  const lvlTwo = definitions[0].levelTwo;

  const [lvl, setLvl] = useState(1);
  const [description, setDescription] = useState("");
  const [messyDescription, setMessyDescription] = useState([]);
  const [textBtnCompare, setTextBtnCompare] = useState("Comprobar");
  const [audio, setAudio] = useState("");
  const [fails, setFails] = useState(0);

  useEffect(() => {
    if (lvl !== 0) {
      setMessyDescription([]);
      initGame(lvl);
    } else {
      setTextBtnCompare("Try Again");
      setMessyDescription([]);
    }
  }, [lvl]);

  useEffect(() => {
    if (description && description.trim() !== "") {
      try {
        let splitDesc = description.split(" ");
        let messy = shuffle(splitDesc);
        setMessyDescription(messy);
      } catch (error) {
        console.log("Error", error);
      }
    }
  }, [description]);

  useEffect(() => {
    if (audio !== "") playAudio(audio); setAudio("");
  }, [audio]);

  useEffect(() => {
    if (fails === 2) {
      alert("Has perdido :(");
      setLvl(0);
      setFails(0);
    }
  }, [fails]);

  /**
   * Función que utilizamos para iniciar el juego.
   * @param {*} lvl le pasamos el parametro que indica en el nivel que nos encontramos para que adquiera la información de un archivo .json u otro.
   */
  const initGame = async (lvl) => {
    let numRandom = 0;
    if (lvl === 1) {
      numRandom = getRandomNum(lvlOne.length);
    } else if (lvl === 2) {
      numRandom = getRandomNum(lvlTwo.length);
    }
    const word = lvl === 1 ? lvlOne[numRandom] : lvlTwo[numRandom];
    await data(word);
  };

  /**
   * Función que utilizamos para cambiar de posición el botón al hacer clic en el
   * @param {*} index le pasamos el indice para saber el botón que hemos clicado y por cual hay que cambiarlo.
   * @param {*} size necesitamos saber la longitud de la array para en caso de clicar el botón que estaa en la posición 0 se cambien por el ultimo.
   */
  const changePos = (index, size) => {
    let arrayDesc = [...messyDescription];

    const temp = arrayDesc[index];

    if (index === 0) {
      arrayDesc[index] = arrayDesc[size - 1];
      arrayDesc[size - 1] = temp;
    } else {
      arrayDesc[index] = arrayDesc[index - 1];
      arrayDesc[index - 1] = temp;
    }

    setMessyDescription(arrayDesc);
  };

  /**
   * Al hacer clic en el botón de comparar llamamos a esta función para saber si es correcto o no.
   */
  const compareDesc = () => {
    if (textBtnCompare === "Comprobar") {
      let check = messyDescription.every(
        (word, index) => word === description.split(" ")[index]
      );
      if (check && lvl === 1) {
        setLvl(2);
      } else if (check && lvl === 2) {
        alert("¡ENHORABUENA! Has ganado");
        setLvl(0);
      } else {
        setFails(fails + 1);
      }
    } else {
      setLvl(1);
      setTextBtnCompare("Comprobar");
    }
  };

  /**
   * Función que utilizamos para llamar a la api
   * @param {*} word la palabra que queremos para buscar.
   */
  const data = async (word) => {
    const response = await getData(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
    );
    setDescription(response[0].meanings[0].definitions[0].definition);
    setAudio(response[0].phonetics[0].audio);
  };

  return (
    <View
      style={{
        justifyContent: "center",
        alignSelf: "center",
        marginVertical: 20,
      }}
    >
      <View style={{ flexDirection: "column" }}>
        <ScrollView
          vertical={true}
          contentContainerStyle={{
            flexDirection: "row",
            flexWrap: "wrap",
            justifyContent: "center",
            marginHorizontal: 'auto'
          }}
        >
          {messyDescription &&
            messyDescription.map((word, index) => {
              return (
                <View key={index} style={{ padding: 2 }}>
                  <TouchableOpacity
                    style={{
                      borderRadius: 8,
                      justifyContent: "center",
                      alignItems: "center",
                      textAlignVertical: "center",
                      width: 80,
                      height: 80,
                      backgroundColor: "blue",
                    }}
                    onPress={() => changePos(index, messyDescription.length)}
                  >
                    <Text style={{ color: "white" }}>{word}</Text>
                  </TouchableOpacity>
                </View>
              );
            })}
        </ScrollView>
        <TouchableOpacity
          style={{
            marginRight: 'auto',
            marginLeft: 'auto',
            borderRadius: 8,
            justifyContent: "center",
            alignItems: "center",
            textAlignVertical: "center",
            width: 80,
            height: 80,
            backgroundColor: "black",
          }}
          onPress={() => compareDesc()}
        >
          <Text style={{ color: "white" }}>{textBtnCompare}</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}
