import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio2() {
  const [text, setText] = useState('');
  const [myArray, setMyArray] = useState([]);

  const handleOnPress = () => {
    if (isNaN(text)) {
      setText('');
      alert('Has introducido texto. Introduce un número.');
      setText('');
    } else if (text === '') {
      alert('No has introducido nada.');
    } else if (!isNaN(text)) {
      alert('Tú número se ha guardado.');
      let newArray = [...myArray];
      newArray.push(text);
      setMyArray(newArray);
      setText('');
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta tu texto..."
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Text style={{ padding: 10, fontSize: 42 }}>{text}</Text>
      <Button title="Pulsa..." onPress={handleOnPress} />
      {myArray.map((value) => (
        <Text>{value}</Text>
      ))}
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
