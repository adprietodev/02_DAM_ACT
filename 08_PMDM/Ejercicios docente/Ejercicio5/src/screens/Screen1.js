import { StyleSheet, Button, Image, View } from 'react-native';
import getData from '../services/services';
import { useState } from 'react';


const Screen1 = () => {
  const [data, setData] = useState();

  const getDatos = async () => {
    let cats = await getData(
      'https://api.thecatapi.com/v1/images/search?size=full'
    );
    setData(cats[0].url);
  };

  return (
    <View style={styles.container}>
      <Image style={styles.image} source={{ uri: data }} />
      <Button onPress={getDatos} title="Pulsame!" />
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

export default Screen1;
