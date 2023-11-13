import { View, Button, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio5() {
  const [color, setColor] = useState('green');
  const [square, setSquare] = useState({ color: 'yellow', width: 200 });

  function handleOnPress() {
    if (color === 'yellow') {
      setColor('green');
      setSquare({ color: 'yellow', width: 200 });
    } else {
      setColor('yellow');
      setSquare({ color: 'green', width: 400 });
    }
  }

  return (
    <View style={[styles.container, { backgroundColor: color }]}>
      <View
        style={[
          styles.square,
          { backgroundColor: square.color, width: square.width },
        ]}
      />
      <Button onPress={handleOnPress} title="Pulsame!" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'green',
    alignItems: 'center',
    justifyContent: 'center',
  },
  square: {
    size: '100',
    mt: '-2',
    marginTop: -6,
    width: 200,
    height: 200,
    backgroundColor: 'yellow',
  },
});
