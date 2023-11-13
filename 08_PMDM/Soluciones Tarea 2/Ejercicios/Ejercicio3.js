import { View, Button, Text, Image, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio3() {
  const [color, setColor] = useState('green');
  const [title, setTitle] = useState('My Title');

  function handleOnPress() {
    if (color === 'yellow') {
      setTitle('My Title');
      setColor('green');
    } else {
      setColor('yellow');
      setTitle('My New Title');
    }
  }

  return (
    <View style={[styles.container, { backgroundColor: color }]}>
      <Text style={styles.title}>{title}</Text>
      <Image
        style={styles.image}
        source={require('../assets/snack-icon.png')}
      />
      <Button onPress={handleOnPress} title="Pulsame!" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'yellow',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 25,
    fontWeight: 'bold',
    fontStyle: 'italic',
    textDecorationLine: 'underline',
  },
  image: {
    width: 200,
    height: 200,
  },
});
