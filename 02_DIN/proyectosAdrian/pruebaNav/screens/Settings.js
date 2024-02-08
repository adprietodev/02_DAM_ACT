import React,{ Component } from "react";
import {View, Text, Button} from 'react-native';

class Settings extends Component {

    render() {
        const { navigation } = this.props;
        return(
            <View>
                <Text>
                    Pantalla Settings
                </Text>
                <Button title='Ir a Home' onPress={() => navigation.navigate('Home')} />
                <Button title='Ir reservations' onPress={() => navigation.navigate('Reservations')} />
                <Button title='Ir login' onPress={() => navigation.navigate('Login')} />
            </View>
        );
    }
}


export default Settings;