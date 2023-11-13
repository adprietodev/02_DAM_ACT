import { View, Button, Text, Image, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio2() {
  const [color, setColor] = useState('green');

  function handleOnPress() {
    color === 'green' ? setColor('blue') : setColor('green');
  }

  return (
    <View style={[styles.container, { backgroundColor: color }]}>
      <Text style={styles.title}>My Title</Text>
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
