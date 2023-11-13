import { StyleSheet, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [squareContents, setSquareContents] = useState([
    {
      widthSquareOne: 150,
      colorSquareOne: 'blue',
      widthSquareTwo: 150,
      colorSquareTwo: 'blue',
    },
    {
      widthSquareOne: 150,
      colorSquareOne: 'blue',
      widthSquareTwo: 150,
      colorSquareTwo: 'red',
    },
    {
      widthSquareOne: 150,
      colorSquareOne: 'red',
      widthSquareTwo: 150,
      colorSquareTwo: 'red',
    },
  ]);

  return (
    <View style={styles.container}>
      {squareContents.map((value, index) => (
        <View key={index.toString()} style={{ flexDirection: 'row' }}>
          <View
            width={value.widthSquareOne}
            height={value.widthSquareOne}
            backgroundColor={value.colorSquareOne}
          />
          <View
            width={value.widthSquareTwo}
            height={value.widthSquareTwo}
            backgroundColor={value.colorSquareTwo}
          />
        </View>
      ))}
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
