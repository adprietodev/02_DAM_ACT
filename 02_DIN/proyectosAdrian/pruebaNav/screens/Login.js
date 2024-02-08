import React, {Component} from 'react';
import {View, Text, Button} from 'react-native';

class Login extends Component {
  render() {
    const {navigation} = this.props;
    return (
      <View>
        <Text>Pantalla Login</Text>
        <Button title="Ir a Home" onPress={() => navigation.navigate('Home')} />
        <Button
          title="Ir reservations"
          onPress={() => navigation.navigate('Reservations')}
        />
        <Button
          title="Ir settings"
          onPress={() => navigation.navigate('Settings')}
        />
      </View>
    );
  }
}

export default Login;
