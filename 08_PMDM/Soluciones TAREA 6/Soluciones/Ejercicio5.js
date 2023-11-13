import { Button, Image, Text, View, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [photo, setPhoto] = useState();
  const [id, setId] = useState();
  const [login, setLogin] = useState();

  const getData = async () => {
    try {
      const response = await fetch(
        'https://api.github.com/search/users?q=David'
      );
      if (response.ok) {
        const resp = await response.json();
        setPhoto(resp.items[0].avatar_url);
        setLogin(resp.items[0].login);
        setId(resp.items[0].id);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <View style={styles.container}>
      <Image style={styles.image} source={{ uri: photo }} />
      <Text>{login}</Text>
      <Text>{id}</Text>
      <Button onPress={getData} title="Pulsame!" />
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
  image: {
    width: 375,
    height: 300,
  },
});
