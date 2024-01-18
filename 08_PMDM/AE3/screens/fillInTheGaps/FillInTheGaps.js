import { TouchableOpacity, Text, TextInput, View } from 'react-native';
import fillInTheGaps from '../../services/data/fill_in_the_gaps.json';
import { Audio } from "expo-av";
import getData from "../../services/Services";
import { useState, useEffect } from 'react';
import getRandomNum from '../../services/RandomNumber';

export default function FillInTheGaps() {

  const lvlOneAdjectives = fillInTheGaps[0].levelOne.adjectives;
  const lvlOneSentences = fillInTheGaps[0].levelOne.sentences;
  const lvlTwoAdjectives = fillInTheGaps[0].levelTwo.adjectives;
  const lvlTwoSentences = fillInTheGaps[0].levelTwo.sentences;

  const [lvl, setLvl] = useState(1);
  const [audio, setAudio] = useState("");
  const [fails, setFails] = useState(0);
  const [adjetive, setAdjetive] = useState("");
  const [sentence, setSentence] = useState("");
  const [textInput, setTextInput] = useState("")
  const [gameStart, setGameStart] = useState(true);

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
      setGameStart(false);
    }
  }, [fails]);

  useEffect(() => {
    if (gameStart) {
      setLvl(1);
      setTextInput("");
    }
  }, [gameStart]);

  useEffect(() => {
    if (audio !== "") playAudio();
  }, [audio]);

  const initGame = (lvl) => {
    let numRandom = 0;
    if (lvl === 1) {
      numRandom = getRandomNum(lvlOneAdjectives.length);
      setAdjetive(lvlOneAdjectives[numRandom]);
      setSentence(lvlOneSentences[numRandom]);
      data(lvlOneAdjectives[numRandom])
    }
    if (lvl === 2) {
      numRandom = getRandomNum(lvlTwoAdjectives.length);
      setAdjetive(lvlTwoAdjectives[numRandom]);
      setSentence(lvlTwoSentences[numRandom]);
      data(lvlTwoAdjectives[numRandom]);
    }
  }

  const compareInputAdjetive = (input) => {
    if (adjetive.toLowerCase() === input.toLowerCase() && lvl === 1) {
      setLvl(2);
      setTextInput("");
    } else if (adjetive.toLowerCase() === input.toLowerCase() && lvl === 2) {
      setLvl(0);

    } else {
      setFails(fails + 1);
    }
  }

  const playAudio = async () => {
    const { sound } = await Audio.Sound.createAsync({ uri: audio });
    await sound.playAsync();
    setAudio("");
  };


  const data = async (adjective) => {
    const response = await getData(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${adjective}`
    );
    setAudio(response[0].phonetics[0].audio);
  };

  return (
    <>
      {gameStart && (
        <View
          style={{
            justifyContent: 'center',
            alignSelf: 'center',
            marginVertical: 80,
          }}
        >
          <View style={{ flexDirection: 'row' }}>
            <View style={{ padding: 2 }}>
              <Text style={{ fontSize: 20, color: 'black' }}>{sentence}</Text>
            </View>
          </View>
          <TextInput
            style={{
              color: 'white',
              borderRadius: 8,
              justifyContent: 'center',
              alignItems: 'center',
              textAlignVertical: 'center',
              backgroundColor: 'black',
              height: 80,
              marginTop: 10,
            }}
            onChangeText={(word) => setTextInput(word)}
            value={textInput}
          />
          <TouchableOpacity
            style={{
              borderRadius: 8,
              justifyContent: 'center',
              alignItems: 'center',
              textAlignVertical: 'center',
              backgroundColor: 'black',
              height: 80,
              marginTop: 10,
            }}
            onPress={() => compareInputAdjetive(textInput)}
          >
            <Text style={{ fontSize: 20, color: 'white' }}>Check!</Text>
          </TouchableOpacity>
        </View>
      )}
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
    </>
  );
}
