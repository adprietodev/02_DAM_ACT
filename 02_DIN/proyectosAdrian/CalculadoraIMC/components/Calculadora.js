import React, { Component, useState } from "react";
import { StyleSheet, View, Text, TextInput, TouchableOpacity, Alert } from "react-native";

const tableIMC = {
    "0-18.4": "Peso insuficiente-lime",
    "18.5-24": "Normopeso-lime",
    "25-27": "Sobrepeso grado I-lime",
    "28-30": "Sobrepeso grado II (preobesidad)-orange",
    "31-35": "Obesidad de tipo I-orange",
    "36-40": "Obesidad de tipo II-orange",
    "41-50": "Obesidad de tipo III (mórbida)-red",
    "51-100": "Obesidad de tipo IV (extrema)-red",
}

export class Calculadora extends Component {

    constructor(props) {
        super(props);
        this.state = {
            weight: 0,
            height: 0,
            textICM: '',
            colorICM: '',
        }
    }
    calculateIMC = () => {
        if (this.state.weight === '' || this.state.height === '') {
            Alert.alert("Error: no es posible de calcular", "Uno de los espacios esta vacío no se puede calcular");
        } else {
            //console.log(typeof (this.state.weight));
            let result = this.state.weight / Math.pow(this.state.height, 2);

            Object.keys(tableIMC).forEach((range) => {
                const [min, max] = range.split('-').map(parseFloat);

                if (min <= result && result <= max) {
                    const [txtResult, color] = tableIMC[range].split('-').map(item => item.trim());
                    this.setState({ textICM: txtResult, colorICM: color });
                    this.props.getResults({ text: txtResult, color: color });
                }

            })
        }
    }

    render() {

        return (
            <View style={{ margin: 10 }}>
                <View style={styles.containerInputFields}>
                    <Text style={styles.labelInputs}>PESO</Text>
                    <TextInput style={styles.inputFields} keyboardType="numeric" onChangeText={(inputWeight) => this.setState({ weight: parseFloat(inputWeight) })} />
                </View>
                <View style={styles.containerInputFields}>
                    <Text style={styles.labelInputs}>ALTURA</Text>
                    <TextInput style={styles.inputFields} keyboardType="numeric" onChangeText={(inputHeight) => this.setState({ height: parseFloat(inputHeight) })} />
                </View>
                <TouchableOpacity style={styles.mainBtn} onPress={this.calculateIMC}>
                    <Text style={styles.txtBtn}>Calcular IMC</Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    inputFields: {
        borderBottomWidth: 1,
        marginBottom: 26,
        fontSize: 18,
    },
    mainBtn: {
        padding: 10,
        backgroundColor: 'white',
        borderWidth: 2,
        borderColor: 'dodgerblue',
        borderRadius: 8,
        alignItems: 'center',
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
    },
    txtBtn: {
        fontSize: 18,
        fontWeight: 'bold',
        color: 'dodgerblue',

    },
    containerInputFields: {
        marginVertical: 0,
    },
    labelInputs: {
        fontWeight: 'bold',
        color: 'blue',
        fontSize: 18,

    }
});