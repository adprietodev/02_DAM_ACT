import { StyleSheet, Button, Image, View } from 'react-native';
import getData from '../services/services';
import { useState } from 'react';


const Screen2 = () => {
  const [data, setData] = useState([]);

  const getDatos = async () => {
    let catOne = await getData(
      'https://api.thecatapi.com/v1/images/search?size=full'
    );
    let catTwo = await getData(
      'https://api.thecatapi.com/v1/images/search?size=full'
    );
    let myArray = [...data];
    myArray[0] = catOne[0].url;
    myArray[1] = catTwo[0].url;
    setData(myArray);
  };

  return (
    <View style={styles.container}>
      <Image style={styles.image} source={{ uri: data[0] }} />
      <Image style={styles.image} source={{ uri: data[1] }} />
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

export default Screen2;
