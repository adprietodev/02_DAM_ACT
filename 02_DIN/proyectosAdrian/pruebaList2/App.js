/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {Component} from 'react';
import { FlatList, ImageBackground } from 'react-native';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';


class App extends Component {
  constructor(props){
    super(props)
    this.state = {
      marcas:[
        {
          nombre: 'Peugeot',
          img: { uri: 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Peugeot_Logo.svg/320px-Peugeot_Logo.svg.png'},
          backgroundColor: 'rgba(255, 0, 0, 0.5)',
        },
        {
          nombre: 'Opel',
          img: {uri: 'https://upload.wikimedia.org/wikipedia/commons/7/7b/Opel-Logo_2017.png'},
          backgroundColor: 'rgba(0, 255, 0, 0.5)',

        },
        {
          nombre: 'Renault',
          img: {uri: 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Renault_2021_Text.svg/1024px-Renault_2021_Text.svg.png'},
          backgroundColor: 'rgba(0, 0, 255, 0.5)',

        },
        {
          nombre: 'Seat',
          img: {uri: 'https://upload.wikimedia.org/wikipedia/commons/8/8b/SEAT_Logo_from_2017.png'},
          backgroundColor: 'rgba(255, 255, 0, 0.5)',
        },
        {
          nombre: 'Fiat',
          img: {uri: 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/FIAT_logo_%282020%29.svg/1280px-FIAT_logo_%282020%29.svg.png'},
          backgroundColor: 'rgba(0, 255, 255, 0.5)',

        },
        {
          nombre: 'Volkswagen',
          img: {uri: 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Volkswagen_logo_2019.svg/600px-Volkswagen_logo_2019.svg.png'},
          backgroundColor: 'rgba(255, 0, 255, 0.5)',
        },
      ]
    }
  }
  render() {
    return (
      <View style={styles.container}>
        <View>
          <Text style={styles.title}>Marcas de automóviles</Text>
        </View>
        <ScrollView>
          <FlatList data={this.state.marcas} keyExtractor={(item, index) => index.toString()} renderItem={({item}) => {
            return(<View>
              <ImageBackground source={item.img}  style={[styles.image, { backgroundColor: item.backgroundColor }]} resizeMode='contain'>
                <Text style={styles.text}>{item.nombre}</Text>
              </ImageBackground>
            </View>)
          }}></FlatList>
        </ScrollView>
        <View>
          <Text style={styles.title}>Prueba hacer scroll</Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  title: {
    textAlign: 'center',
    fontSize: 24,
    color: 'black',
    margin: 18,
  },
  image: {
    borderWidth: 4,
    padding: 6,
    margin: 10,
    flex: 1,
  },
  text: {
    color: 'black',
    fontSize: 18,
    height: 80,
    textAlign: 'left',
    paddingLeft: 8,
    
  },
});

export default App;
