import { TouchableOpacity, Text, View } from 'react-native';
import { useState, useEffect } from 'react';
import listening from '../../services/data/listening.json';
import getData from '../../services/Services';
import wordsListening from '../../services/data/fill_in_the_gaps.json';

export default function Listening() {

  const lvlOne = wordsListening[0].levelOne;
  const lvlTwo = wordsListening[0].levelTwo;

  const [lvl, setLvl] = useState(1);
  const [countTime, setCountTime] = useState(0);
  const [randomWords, setRandomWords] = useState([]);

  useEffect(() => {
    if (lvl !== 0) {
      initGame(lvl);
    } else {
    }
  }, [lvl]);

  const initGame = async (lvl) => {
    //console.log("Iniciamos game");
    let numRandom = 0;
    if (lvl === 1) {
      numRandom = Math.floor(Math.random() * lvlOne.length);
      let tempArray = []
      let count = 0;

      while (count != 4) {
        if (!tempArray.includes(lvlOne[numRandom])) {
          tempArray.push(lvlOne[numRandom]);
          count++;
        }
      }
    } else if (lvl === 2) {
      numRandom = Math.floor(Math.random() * lvlTwo.length);
      while (count != 9) {
        if (!tempArray.includes(lvlTwo[numRandom])) {
          tempArray.push(lvlTwo[numRandom]);
          count++;
        }
      }
    }





  };

  const moveBtns = setInterval(() => {
    //100 equivale a  0.1s
    setCountTime(countTime + 100);

    //Logica que queramos realizar

    //Si llegamos a 3000 o mas que son 3s para de reproducirse.
    if (countTime >= 3000) {
      clearInterval(moveBtns);
      //Aquí ejecutariamos el playAudio.
    }

  }, 100)

  return (
    <View style={{ flexDirection: 'row' }}>
      <View style={{ padding: 2 }}>
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
        >
          <Text style={{ color: 'white' }}>Hola</Text>
        </TouchableOpacity>
      </View>
      <View style={{ padding: 2 }}>
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
        >
          <Text style={{ color: 'white' }}>Hola</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}
