import { TouchableOpacity, Text, View } from 'react-native';
import { useState, useEffect } from 'react';
import { Audio } from "expo-av";
import getData from '../../services/Services';
import wordsListening from '../../services/data/listening.json';

export default function Listening() {

  const lvlOne = wordsListening[0].levelOne;
  const lvlTwo = wordsListening[0].levelTwo;

  const [lvl, setLvl] = useState(1);
  const [drawTable, setDrawTable] = useState([]);
  const [startShuffle, setStartShuffle] = useState(false);
  const [audio, setAudio] = useState("");
  const [selectedWord, setSelectedWord] = useState("");
  const [fails, setFails] = useState(0);

  useEffect(() => {
    if (lvl !== 0) {
      initGame(lvl);
    } else {
      if (fails < 2) {
        alert("!ENHORABUENA HAS GANADO! :)")
      }
      setDrawTable([["Try Again"]]);
    }
  }, [lvl]);

  useEffect(() => {
    if (startShuffle) {
      moveBtns();
      setStartShuffle(false);
    }
  }, [startShuffle])

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

  const initGame = (lvl) => {
    let numRandom = 0;
    let tempArray = [];
    let drawTemps = [];
    let count = 0;

    if (lvl === 1) {
      drawTemps = [[null, null], [null, null]];
      while (count != 4) {
        numRandom = getRandomNum(lvlOne.length);
        if (!tempArray.includes(lvlOne[numRandom])) {
          tempArray.push(lvlOne[numRandom]);
          count++;
        }
      }
      let index = 0;

      drawTemps = drawTemps.map(row =>
        row.map(() => tempArray[index++])
      );

    } else if (lvl === 2) {
      drawTemps = [[null, null, null], [null, null, null], [null, null, null]];
      while (count != 9) {
        numRandom = getRandomNum(lvlTwo.length);
        if (!tempArray.includes(lvlTwo[numRandom])) {
          tempArray.push(lvlTwo[numRandom]);
          count++;
        }
      }

      let index = 0;

      drawTemps = drawTemps.map(row =>
        row.map(() => tempArray[index++])
      );
    }
    setDrawTable(drawTemps);
    setStartShuffle(true);
  };

  const moveBtns = () => {
    let contador = 0;
    const interval = 100;
    const callInterval = setInterval(() => {
      contador += interval;
      // Lógica que queremos realizar
      let shuffledArray = [...drawTable];
      const numRows = shuffledArray.length;
      const numCols = shuffledArray[0].length;

      const flattenArray = shuffledArray.flat(); // Convertimos la matriz en un array plano

      for (let i = flattenArray.length - 1; i > 0; i--) {
        const j = getRandomNum((i + 1));
        [flattenArray[i], flattenArray[j]] = [flattenArray[j], flattenArray[i]];
      }

      // Restauramos la forma original de la matriz
      shuffledArray = [];
      for (let i = 0; i < numRows; i++) {
        shuffledArray.push(flattenArray.slice(i * numCols, (i + 1) * numCols));
      }

      setDrawTable(shuffledArray)

      // Si llegamos a 3000 o más que son 3s para de reproducirse.
      if (contador >= 3000) {
        clearInterval(callInterval);
        // Aquí ejecutaríamos la función que selecciona la palabra.
        let numRandom = getRandomNum(drawTable.flat().length);
        data(drawTable.flat()[numRandom]);
      }
    }, interval);

  };

  const compareWordSound = (word) => {
    if (word !== "Try Again") {
      if (word === selectedWord && lvl === 1) {
        setLvl(2)
      } else if (word === selectedWord && lvl === 2) {
        setLvl(0);
      } else {
        setFails(fails + 1);
      }
    } else {
      setLvl(1);
    }

  }

  const data = async (word) => {
    const response = await getData(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
    );
    setAudio(response[0].phonetics[0].audio);
    setSelectedWord(word);
  };

  const playAudio = async () => {
    const { sound } = await Audio.Sound.createAsync({ uri: audio });
    await sound.playAsync();
    setAudio("");
  };

  const getRandomNum = (num) => {
    return Math.floor(Math.random() * num)
  }

  return (
    <View style={{ marginHorizontal: "auto", marginTop: 60, justifyContent: "center", alignItems: "center", }}>
      {drawTable && drawTable.map((item, rowIndex) => (
        <View key={rowIndex} style={{ flexDirection: 'row' }}>
          {item.map((word, columnIndex) => (
            <View key={columnIndex} style={{ padding: 2 }}>
              <TouchableOpacity
                style={{
                  borderRadius: 8,
                  justifyContent: 'center',
                  alignItems: 'center',
                  textAlignVertical: 'center',
                  width: 80,
                  height: 80,
                  backgroundColor: 'blue',
                }}
                onPress={() => compareWordSound(word)}
              >
                <Text style={{ color: 'white' }}>{word}</Text>
              </TouchableOpacity>
            </View>
          ))}
        </View>
      ))}
    </View>
  );

}
