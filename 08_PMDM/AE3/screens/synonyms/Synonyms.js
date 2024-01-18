import { TouchableOpacity, Text, View } from 'react-native';
import synonyms from '../../services/data/synonyms.json';
import { Audio } from "expo-av";
import getData from "../../services/Services";
import getRandomNum from '../../services/RandomNumber';
import { useState, useEffect } from 'react';

export default function Synonyms() {

  const lvlOne = synonyms[0].levelOne;
  const lvlTwo = synonyms[0].levelTwo;

  const [lvl, setLvl] = useState(1);
  const [audio, setAudio] = useState("");
  const [fails, setFails] = useState(0);
  const [gameStart, setGameStart] = useState(true);
  const [words, setWords] = useState([]);
  const [selectedWord, setSelectedWord] = useState("");

  useEffect(() => {
    if (lvl !== 0) {
      initGame(lvl);
    } else {
      if (fails < 2) {
        alert("!ENHORABUENA HAS GANADO! :)")
      }
      setFails(0);
      setGameStart(false);
    }
  }, [lvl]);

  useEffect(() => {
    if (fails === 2) {
      alert("Has perdido :(");
      setLvl(0);
      setWords([]);
      setGameStart(false);
    }
  }, [fails]);

  useEffect(() => {
    if (gameStart) {
      setLvl(1);
    }
  }, [gameStart]);

  useEffect(() => {
    if (audio !== "") playAudio();
  }, [audio]);

  const initGame = async (lvl) => {
    let numRandom = 0;
    let tempArray = [];

    if (lvl === 1) {
      numRandom = getRandomNum(lvlOne.length);
      tempArray = [...lvlOne];
      let auxArr = [];
      lvlOne.map(async (word) => {
        let synonym = await dataSynonyms(word);
        auxArr.push(synonym);
      })
      setWords(auxArr);
      let selec = await dataSynonyms(lvlOne[numRandom])
      console.log(selec, lvlOne[numRandom])
      setSelectedWord(selec);
      dataAudio(lvlOne[numRandom]);
    }
    if (lvl === 2) {
      numRandom = getRandomNum(lvlTwo.length);
      tempArray = [...lvlTwo];
      let auxArr = [];
      lvlTwo.map(async (word) => {
        let synonym = await dataSynonyms(word);
        auxArr.push(synonym);
      })
      setWords(auxArr);
      let selec = await dataSynonyms(lvlTwo[numRandom])
      console.log(selec, lvlTwo[numRandom])
      setSelectedWord(selec);
      dataAudio(lvlTwo[numRandom]);
    }
  }

  const compareAudioBtn = (btnWord) => {
    if (btnWord === selectedWord && lvl === 1) {
      setLvl(2);
    } else if (btnWord === selectedWord && lvl === 2) {
      setLvl(0);
      setGameStart(false);
      setWords([]);
    } else {
      setFails(fails + 1);
    }
  }

  const playAudio = async () => {
    const { sound } = await Audio.Sound.createAsync({ uri: audio });
    await sound.playAsync();
    setAudio("");
  };


  const dataSynonyms = async (word) => {
    const response = await getData(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
    );
    return response[0].meanings[0].synonyms[0];
  };

  const dataAudio = async (word) => {
    const response = await getData(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
    );
    setAudio(response[0].phonetics[0].audio);
  };

  return (
    <View
      style={{
        justifyContent: 'center',
        alignSelf: 'center',
        marginVertical: 80,
        marginHorizontal: 'auto'
      }}
    >
      <View style={{ flexDirection: 'row', flexWrap: 'wrap', marginHorizontal: 'auto', justifyContent: 'center' }}>
        {gameStart && words && words.map((word, index) => {
          return (
            <View key={index} style={{ padding: 2 }}>
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
                onPress={() => compareAudioBtn(word)}
              >
                <Text style={{ color: 'white' }}>{word}</Text>
              </TouchableOpacity>
            </View>)
        })}

        {!gameStart && (
          <View style={{ marginHorizontal: "auto", marginTop: 60, justifyContent: "center", alignItems: "center", }}>
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
              onPress={() => setGameStart(true)}
            >
              <Text style={{ fontSize: 16, color: 'white' }}>Try Again!</Text>
            </TouchableOpacity>
          </View>
        )}
      </View>
    </View>
  );
}
