import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio5() {
  const [text, setText] = useState('');
  const [email, setEmail] = useState();

  function handleOnPress() {
    let split = text.split('');
    let ats = split.filter((x) => x === '@');
    let firstAt = ats.indexOf('@');
    let emailTwo = text.substring(firstAt + 1, text.length);
    emailTwo = emailTwo.split('');
    let puntos = emailTwo.filter((x) => x === '.');

    if (text === '') {
      alert('Por favor, introduce tú email.');
      setText('');
    } else if (ats.length === 0) {
      alert('Por favor, introduce un email con una arroba.');
      setText('');
    } else if (ats.length > 1) {
      alert('Por favor, introduce un email con una sóla arroba.');
      setText('');
    } else if (puntos.length > 1) {
      alert('Por favor, introduce un email con un solo punto tras la arroba.');
      setText('');
    } else if (text[text.length - 1] === '.') {
      alert('Por favor, introduce un email que no acabe en punto.');
      setText('');
    } else if (puntos.length === 0) {
      alert('Por favor, introduce un email con un punto tras la arroba.');
      setText('');
    } else if (text[0] === '@') {
      alert('Por favor, introduce un email que no empiece por arroba.');
      setText('');
    } else {
      setEmail('Email correcto: ' + text);
      setText('');
    }
  }

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 30 }}>Validador e-mail</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta e-mail"
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Text style={{ padding: 10, fontSize: 42 }}>{email}</Text>
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
