import { View, Button, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio6() {
  const [square, setSquare] = useState({
    color: 'green',
    width: 150,
    height: 150,
  });

  function handleOnPress() {
    if (square.color === 'yellow') {
      setSquare({
        color: 'green',
        width: square.width + 10,
        height: square.height + 10,
      });
    } else {
      setSquare({
        color: 'yellow',
        width: square.width - 20,
        height: square.height - 20,
      });
    }
  }

  return (
    <View style={styles.container}>
      <View
        style={[
          styles.square,
          {
            width: square.width,
            height: square.height,
            backgroundColor: square.color,
          },
        ]}
      />
      <Button onPress={handleOnPress} title="Pulsame!" />
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
  square: {
    size: '100',
    mt: '-2',
    marginTop: -6,
    width: 200,
    height: 200,
    backgroundColor: 'yellow',
  },
});
