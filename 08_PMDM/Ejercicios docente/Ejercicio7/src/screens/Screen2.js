import {
  StyleSheet,
  Text,
  Button,
  View,
  Image
} from 'react-native';
import getData from '../services/services';
import { useState } from 'react';


const Screen2 = () => {
  const [name, setName] = useState();
  const [latLong, setLatLong] = useState([]);
  const [population, setPopulation] = useState();
  const [photo, setPhoto] = useState();
  const [capital, setCapital] = useState();


  const getInfo = async () => {
    let info = await getData(
      'https://restcountries.com/v3.1/name/france'
    );    
    setPhoto(info[0].coatOfArms.png);
    setName(info[0].name.nativeName.fra.official);
    setPopulation(info[0].population);
    setCapital(info[0].capital[0])
    setLatLong(info[0].capitalInfo.latlng)
  };

  return (
    <View style={styles.container}>
      <Button onPress={getInfo} title="Mostrar información" />
      <Image style={styles.image} source={{ uri: photo }} />
      <Text>País: {name}</Text>
      <Text>Capital: {capital}</Text>
      <Text>Población: {population}</Text>
      <Text>Latitud y longitud: {latLong[0] + "/" + latLong[1]}</Text>
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