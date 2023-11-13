import { Text, View, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio11() {
  const [textOne, setTextOne] = useState('text');
  const [textTwo, setTextTwo] = useState('text');
  const [textThree, setTextThree] = useState('text');
  const [textFour, setTextFour] = useState('text');

  function handleOnPress(num) {
      if (num === 0) {
        if(textOne === 'text'){
          setTextOne('changed text');
        } else {
          setTextOne('text');
        }
      } else if (num === 1) {
        if(textTwo === 'text'){
          setTextTwo('changed text');
        } else {
          setTextTwo('text');
        }
      } else if (num === 2) {
        if(textThree === 'text'){
          setTextThree('changed text');
        } else {
          setTextThree('text');
        }
      } else if (num === 3) {
        if(textFour === 'text'){
          setTextFour('changed text');
        } else {
          setTextFour('text');
        }
      }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.text} onPress={() => handleOnPress(0)}>
        {textOne}
      </Text>
      <Text style={styles.text} onPress={() => handleOnPress(1)}>
        {textTwo}
      </Text>
      <Text style={styles.text} onPress={() => handleOnPress(2)}>
        {textThree}
      </Text>
      <Text style={styles.text} onPress={() => handleOnPress(3)}>
        {textFour}
      </Text>
    </View>
  );
}



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    fontSize: 30,
    marginBottom: 20,
  },
});



