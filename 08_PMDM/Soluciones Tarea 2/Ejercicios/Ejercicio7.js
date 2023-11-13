import { View, Button, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio7() {
  const [dimensions, setDimensions] = useState({
    color: 'green',
    width: 150,
    height: 150,
  });

  function handleOnPress() {
    if (dimensions.color === 'yellow' && dimensions.height < 390) {
      setDimensions({
        color: 'green',
        width: dimensions.width + 10,
        height: dimensions.height + 10,
      });
    } else if (dimensions.color === 'yellow' && dimensions.height >= 390) {
      setDimensions({
        color: 'green',
        width: dimensions.width - 20,
        height: dimensions.height - 20,
      });
    } else if (dimensions.color === 'green' && dimensions.height < 390) {
      setDimensions({
        color: 'yellow',
        width: dimensions.width + 20,
        height: dimensions.height + 20,
      });
    } else if (dimensions.color === 'green' && dimensions.height >= 390) {
      setDimensions({
        color: 'yellow',
        width: dimensions.width - 10,
        height: dimensions.height - 10,
      });
    }
  }

  return (
    <View style={styles.container}>
      <View
        style={[
          styles.square,
          {
            width: dimensions.width,
            height: dimensions.height,
            backgroundColor: dimensions.color,
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
