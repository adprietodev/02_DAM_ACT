import { View, Button, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio10() {
  const [conditions, setConditions] = useState({ color: 'white', limit: 390 });
  const [square, setSquare] = useState({
    color: 'green',
    width: 150,
    height: 150,
  });

  function handleOnPress() {
    if (square.color === 'yellow' && square.height <= conditions.limit) {
      setConditions({ color: 'white', limit: 390 });
      setSquare({
        color: 'green',
        width: square.width + 10,
        height: square.height + 10,
      });
    } else if (square.color === 'yellow' && square.height > conditions.limit) {
      setConditions({ color: 'white', limit: 150 });
      setSquare({
        color: 'green',
        width: square.width - 10,
        height: square.height - 10,
      });
    } else if (square.color === 'green' && square.height <= conditions.limit) {
      setConditions({ color: 'black', limit: 390 });
      setSquare({
        color: 'yellow',
        width: square.width + 20,
        height: square.height + 20,
      });
    } else if (square.color === 'green' && square.height > conditions.limit) {
      setConditions({ color: 'black', limit: 150 });
      setSquare({
        color: 'yellow',
        width: square.width - 20,
        height: square.height - 20,
      });
    }
  }

  return (
    <View style={[styles.container, { backgroundColor: conditions.color }]}>
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
      <Button style={styles.button} onPress={handleOnPress} title="Pulsame!" />
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
  button: {
    width: 200,
    height: 200,
  },
});
