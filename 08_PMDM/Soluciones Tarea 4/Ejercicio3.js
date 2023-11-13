import { StyleSheet, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [squareContents, setSquareContents] = useState([
    {
      widthSquareOne: 50,
      colorSquareOne: 'blue',
      widthSquareTwo: 50,
      colorSquareTwo: 'blue',
    },
    {
      widthSquareOne: 50,
      colorSquareOne: 'blue',
      widthSquareTwo: 50,
      colorSquareTwo: 'blue',
    },
    {
      widthSquareOne: 100,
      colorSquareOne: 'blue',
      widthSquareTwo: 100,
      colorSquareTwo: 'blue',
    },
  ]);

  return (
    <View style={styles.container}>
      <View style={{ flexDirection: 'row' }}>
        <View
          width={0}
          height={0}
          backgroundColor={'transparent'}
          borderStyle={'solid'}
          borderLeftWidth={50}
          borderRightWidth={50}
          borderBottomWidth={100}
          borderLeftColor="transparent"
          borderRightColor="transparent"
          borderBottomColor="blue"
        />
      </View>
      {squareContents.map((value, index) => (
        <View key={index.toString()} style={{ flexDirection: 'row' }}>
          <View
            width={value.widthSquareTwo}
            height={value.widthSquareTwo}
            backgroundColor={value.colorSquareTwo}
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
