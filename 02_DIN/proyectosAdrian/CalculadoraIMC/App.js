/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { Component } from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import { Calculadora } from './components/Calculadora';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      txtResult: '',
      colorResult: 'black'
    };
  }

  getCalculatorResults = ({ text, color }) => {
    this.setState({ txtResult: text, colorResult: color })
  }

  render() {
    return (
      <View style={styles.mainContainer}>
        <View style={{ alignItems: 'center' }}>
          <Text style={styles.title}>Calculadora IMC</Text>
        </View>
        <View style={styles.panelContainer}>
          <View style={styles.lblsContainers}>
            <Text style={styles.title}>Datos</Text>
          </View>
          <Calculadora getResults={this.getCalculatorResults} />
          <View style={styles.lblsContainers}>
            <Text style={styles.labelResult}>Resultado</Text>
            <Text style={{ color: this.state.colorResult, fontSize: 16, marginBottom: 30, }}>{this.state.txtResult}</Text>
          </View>
        </View>
        <View>
          <Text style={styles.sign}>Created for 2ª DAM - Adrián Prieto Villena</Text>
        </View>
      </View>
    );
  }

}

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    backgroundColor: 'indigo',
  },
  panelContainer: {
    backgroundColor: 'white',
    margin: 6,
  },
  title: {
    color: 'red',
    fontSize: 32,
    marginVertical: 16,
  },
  lblsContainers: {
    marginHorizontal: 20,

  },
  sign: {
    fontSize: 18,
    color: 'grey',
    margin: 6
  },
  labelResult: {
    fontSize: 16,
    color: 'black'
  }
});

export default App;
