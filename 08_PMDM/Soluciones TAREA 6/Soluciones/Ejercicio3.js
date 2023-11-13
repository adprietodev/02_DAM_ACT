import { Button, View, StyleSheet } from 'react-native';

export default function App() {
  const getDatos = async () => {
    try {
      const response = await fetch(
        'https://api.github.com/search/users?q=Java'
      );
      if (response.ok) {
        const resp = await response.json();
        console.log(resp.items[0]);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <View style={styles.container}>
      <Button onPress={getDatos} title="Pulsame!" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#ecf0f1',
    padding: 8,
  },
});
