import { TouchableOpacity, Text, View, ScrollView } from "react-native";
import { useEffect, useState } from "react";
import { Audio } from "expo-av";
import definitions from "../../services/data/definitions.json";
import getData from "../../services/Services";
import shuffleDefinition from "./ShuffleDefinitions";

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
        //console.log(splitDesc.length);
        let messy = shuffleDefinition(splitDesc);
        //console.log("Split desc: ", splitDesc, messy);
        setMessyDescription(messy);
      } catch (error) {
        console.log("Error", error);
      }
    }
  }, [description]);

  useEffect(() => {
    if (audio !== "") playAudio();
  }, [audio]);

  useEffect(() => {
    if (fails === 2) {
      alert("Has perdido :(");
      setLvl(0);
      setFails(0);
    }
  }, [fails]);

  const initGame = async (lvl) => {
    //console.log("Iniciamos game");
    let numRandom = 0;
    if (lvl === 1) {
      numRandom = Math.floor(Math.random() * lvlOne.length);
    } else if (lvl === 2) {
      numRandom = Math.floor(Math.random() * lvlTwo.length);
    }

    const word = lvl === 1 ? lvlOne[numRandom] : lvlTwo[numRandom];
    console.log(word);
    await data(word);
  };

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

  const data = async (word) => {
    const response = await getData(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
    );
    setDescription(response[0].meanings[0].definitions[0].definition);
    setAudio(response[0].phonetics[0].audio);
  };

  const playAudio = async () => {
    const { sound } = await Audio.Sound.createAsync({ uri: audio });
    await sound.playAsync();
    setAudio("");
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
