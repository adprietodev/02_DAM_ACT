import { StyleSheet, View } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [contents, setContents] = useState([
    [
      {
        topLeftRadius: 150,
        topRightRadius: 0,
        bottomLeftRadius: 0,
        bottomRightRadius: 0,
        color: 'red',
      },
      {
        topLeftRadius: 0,
        topRightRadius: 150,
        bottomLeftRadius: 0,
        bottomRightRadius: 0,
        color: 'blue',
      },
    ],
    [
      {
        topLeftRadius: 0,
        topRightRadius: 0,
        bottomLeftRadius: 150,
        bottomRightRadius: 0,
        color: 'blue',
      },
      {
        topLeftRadius: 0,
        topRightRadius: 0,
        bottomLeftRadius: 0,
        bottomRightRadius: 150,
        color: 'red',
      },
    ],
  ]);

  return (
    <View style={styles.container}>
      {[150, 100, 75].map((size) =>
        [0, 1].map((row) => (
          <View key={row.toString()} style={{ flexDirection: 'row' }}>
            {[0, 1].map((column) => (
              <View
                width={size}
                height={size}
                borderTopLeftRadius={contents[row][column].topLeftRadius}
                borderTopRightRadius={contents[row][column].topRightRadius}
                borderBottomLeftRadius={contents[row][column].bottomLeftRadius}
                borderBottomRightRadius={
                  contents[row][column].bottomRightRadius
                }
                backgroundColor={contents[row][column].color}
              />
            ))}
          </View>
        ))
      )}
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
