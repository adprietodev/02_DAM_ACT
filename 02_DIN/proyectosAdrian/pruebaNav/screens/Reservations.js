import React,{ Component } from "react";
import {View, Text, Button} from 'react-native';


class Reservations extends Component {

    render() {
        const { navigation } = this.props;
        return(
            <View>
                <Text>
                    Pantalla Reservations
                </Text>
                <Button title='Ir a Home' onPress={() => navigation.navigate('Home')} />
                <Button title='Ir login' onPress={() => navigation.navigate('Login')} />
                <Button title='Ir settings' onPress={() => navigation.navigate('Settings')} />
            </View>
        );
    }
}


export default Reservations;