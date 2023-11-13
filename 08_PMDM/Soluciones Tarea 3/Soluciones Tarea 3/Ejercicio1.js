import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio1() {
  const [text, setText] = useState('');

  function handleOnPress() {
    if (isNaN(text)) {
      setText('');
      alert('Has introducido texto');
    } else if (text === '') {
      alert('No has introducido nada');
    } else if (!isNaN(text)) {
      alert('Has introducido un número');
      setText('');
    }
  }

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
