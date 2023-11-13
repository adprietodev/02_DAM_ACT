import { Button, Image, TextInput, Text, View, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [data, setData] = useState();
  const [searchTerm, setSearchTerm] = useState('David');
  const [position, setPosition] = useState(0);
  const [photo, setPhoto] = useState();
  const [id, setId] = useState();
  const [login, setLogin] = useState();

  const getData = async () => {
    try {
      const response = await fetch(
        `https://api.github.com/search/users?q=${searchTerm}`
      );
      if (response.ok) {
        const resp = await response.json();
        setData(resp.items);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const getProfile = () => {
    setPhoto(data[position].avatar_url);
    setLogin(data[position].login);
    setId(data[position].id);
  };

  return (
    <View style={styles.container}>
      <Image style={styles.image} source={{ uri: photo }} />
      <Text>{login}</Text>
      <Text>{id}</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta término de búsqueda"
        onChangeText={(newText) => setSearchTerm(newText)}
        defaultValue={searchTerm}
      />
      <Button onPress={getData} title="Buscar perfiles" />
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta posición"
        onChangeText={(newText) => setPosition(newText)}
        defaultValue={position}
      />
      <Button onPress={getProfile} title="Mostrar perfil" />
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
