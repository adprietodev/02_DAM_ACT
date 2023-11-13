import { Image, Button, View, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [photo, setPhoto] = useState();
  const [photoTwo, setPhotoTwo] = useState();

  const getData = async () => {
    try {
      const response = await fetch(
        'https://api.thecatapi.com/v1/images/search?size=full'
      );
      const responseDos = await fetch(
        'https://api.thecatapi.com/v1/images/search?size=full'
      );
      if (response.ok && responseDos.ok) {
        const data = await response.json();
        const dataTwo = await responseDos.json();
        setPhoto(data[0].url);
        setPhotoTwo(dataTwo[0].url);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <Image style={styles.image} source={{ uri: photo }} />
      <Image style={styles.image} source={{ uri: photoTwo }} />
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
