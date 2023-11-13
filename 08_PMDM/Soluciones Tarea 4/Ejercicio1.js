import { StyleSheet, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [contents, setContents] = useState([
    { width: 150, color: 'blue' },
    { width: 300, color: 'red' },
  ]);

  return (
    <View style={styles.container}>
      {contents.map((value, index) => (
        <View key={index.toString()} style={{ flexDirection: 'row' }}>
          <View
            width={value.width}
            height={value.width}
            backgroundColor={value.color}
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
