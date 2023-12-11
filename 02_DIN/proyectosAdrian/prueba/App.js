/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {Component} from 'react';
import {View, Text, StyleSheet} from 'react-native';

class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <View style={styles.seccion1}>
          <Text>seccion1</Text>
        </View>
        <View style={styles.seccion2}>
          <Text>seccion2</Text>
          <View style={{backgroundColor: 'red', height: 50}}></View>
          <View
            style={{
              backgroundColor: 'green',
              width: 50,
              height: 50,
            }}></View>
          <View style={{backgroundColor: 'blue', width: 50, height: 50}}></View>
        </View>
        <View style={styles.seccion3}>
          <Text>seccion3</Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    borderColor: 'purple',
    borderWidth: 5,
  },
  seccion1: {
    flex: 1,
    borderColor: 'red',
    borderWidth: 3,
    fontSize: 12,
    fontWeight: '600',
    margin: 4,
    padding: 12,
    textAlign: 'right',
  },
  seccion2: {
    flex: 0.5,
    flexWrap: 'wrap-reverse',
    alignContent: 'stretch',
    justifyContent: 'space-around',
    alignItems: 'stretch',
    borderColor: 'green',
    borderWidth: 3,
    fontSize: 12,
    fontWeight: '600',
    margin: 4,
    padding: 12,
    textAlign: 'right',
  },
  seccion3: {
    flex: 1,
    borderColor: 'blue',
    borderWidth: 3,
    fontSize: 12,
    fontWeight: '600',
    margin: 4,
    padding: 12,
    textAlign: 'right',
  },
});

export default App;
