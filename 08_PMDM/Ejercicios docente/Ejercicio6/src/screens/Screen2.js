import { StyleSheet, ScrollView, Button, Image, View } from 'react-native';
import getData from '../services/services';
import { useState } from 'react';

const Screen2 = () => {
  const [data, setData] = useState([]);

  const getCats = async () => {
    let cats = await getData(
      'https://api.thecatapi.com/v1/images/search?limit=10'
    );
    setData(cats);
  };

  const Cats = () => {
    return data.map((element) => (
      <Image style={styles.image} source={{ uri: element.url }} />
    ));
  };

  return (
    <View style={styles.container}>
      <Button onPress={getCats} title="Pulsame!" />
      <ScrollView>
        <Cats />
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

export default Screen2;
