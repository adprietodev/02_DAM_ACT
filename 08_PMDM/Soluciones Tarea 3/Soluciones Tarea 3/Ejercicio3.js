import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio3() {
  const [text, setText] = useState('');
  const [dollars, setDollars] = useState();

  function handleOnPress() {
    if (isNaN(text)) {
      alert('Has introducido texto');
      setDollars('');
    } else if (text === '') {
      alert('No has introducido nada');
      setDollars('');
    } else if (!isNaN(text)) {
      let result = text * 0.98;
      setDollars(result.toFixed(2) + ' dólares');
    }
    setText('');
  }

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 30 }}>Conversor euros - dólares</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta euros"
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Text style={{ padding: 10, fontSize: 42 }}>{dollars}</Text>
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
