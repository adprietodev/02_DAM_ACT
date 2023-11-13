import { StyleSheet, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [contents, setContents] = useState([
    ['red', 'blue', 'green'],
    ['blue', 'green', 'red'],
    ['green', 'red', 'blue'],
  ]);

  return (
    <View style={styles.container}>
      {[0, 1, 2].map((value, index) => (
        <View style={{ flexDirection: 'row' }}>
          {contents[value].map((value, index) => (
            <View
              width={100}
              height={100}
              borderRadius={50}
              backgroundColor={value}
            />
          ))}
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
