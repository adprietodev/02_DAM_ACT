import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio2() {
  const [text, setText] = useState('');
  const [miles, setMiles] = useState();

  function handleOnPress() {
    if (isNaN(text)) {
      alert('Has introducido texto');
      setMiles('');
    } else if (text === '') {
      alert('No has introducido nada');
      setMiles('');
    } else if (!isNaN(text)) {
      let result = text * 0.621371;
      setMiles(result.toFixed(2) + ' millas');
    }
    setText('');
  }

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 30 }}>Conversor de km a millas</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta kilómetros"
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Text style={{ padding: 10, fontSize: 42 }}>{miles}</Text>
      <Button title="Pulsa..." onPress={handleOnPress} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
