import {
  StyleSheet,
  Text,
  TextInput,
  ScrollView,
  Button,
  Image,
  View,
} from 'react-native';
import getData from '../services/services';
import { useState } from 'react';

const ScreenStack2 = (props) => {
  const [data, setData] = useState([]);
  const [searchTerm, setSearchTerm] = useState('David');

  const getProfiles = async () => {
    let profiles = await getData(
      `https://api.github.com/search/users?q=${searchTerm}`
    );
    setData(profiles.items);
  };

  const Profiles = () => {
    return data.map((element) => (
      <View style={styles.container}>
        <Image style={styles.image} source={{ uri: element.avatar_url }} />
        <Text>{element.login}</Text>
        <Text>{element.id}</Text>
      </View>
    ));
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta término de búsqueda"
        onChangeText={(newText) => setSearchTerm(newText)}
        defaultValue={searchTerm}
      />
      <Button onPress={getProfiles} title="Mostrar perfiles" />
      <ScrollView>
        <Profiles />
      </ScrollView>
    </View>
  );
};

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

export default ScreenStack2;

