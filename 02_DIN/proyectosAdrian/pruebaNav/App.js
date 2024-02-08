import React, { Component } from 'react';

import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import {
  Button,
  StyleSheet,
  Text,
  View,
} from 'react-native';

import Login  from './screens/Login';
import Reservations from './screens/Reservations';
import  Settings  from './screens/Settings';

const navegationStack = createStackNavigator();

const HomeScreen = ({ navigation }) => {
  return (
    <View>
      <Text>Home Screen</Text>
      <Button title='Ir login' onPress={() => navigation.navigate('Login')} />
      <Button title='Ir reservations' onPress={() => navigation.navigate('Reservations')} />
      <Button title='Ir settings' onPress={() => navigation.navigate('Settings')} />
    </View>
  );
};

class App extends Component {
  render () {
    return(
      <NavigationContainer>
        <navegationStack.Navigator  screenOptions={({ route, navigation }) => ({headerShown: false,})}>
          <navegationStack.Screen name='Home' component={HomeScreen} />
          <navegationStack.Screen name='Login' component={Login} />
          <navegationStack.Screen name='Reservations' component={Reservations} />
          <navegationStack.Screen name='Settings' component={Settings} />
        </navegationStack.Navigator>
      </NavigationContainer>
    ); 
  }
}



const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
