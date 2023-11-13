import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio4() {
  const [text, setText] = useState('');
  const [dni, setDni] = useState();

  function handleOnPress() {
    let id = text.toString();
    id = id.split('');
    if (text === '') {
      alert('No has introducido nada.');
      setDni('');
    } else if (text.length !== 9) {
      alert('Introduce un DNI con ocho cifras y una letra.');
      setDni('');
    } else if (!isNaN(parseInt(id[id.length - 1]))) {
      alert('El DNI tiene que acabar en letra.');
      setDni('');
    } else if (isNaN(text.substring(0, 8))) {
      alert('Introduce un DNI con ocho cifras numéricas.');
      setDni('');
    } else {
      if (dni === '') {
        setDni('DNI correcto: ' + text);
      }
    }
    setText('');
  }

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 30 }}>Validador DNI</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta DNI"
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Text style={{ padding: 10, fontSize: 42 }}>{dni}</Text>
      <Button title="Validar" onPress={handleOnPress} />
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
