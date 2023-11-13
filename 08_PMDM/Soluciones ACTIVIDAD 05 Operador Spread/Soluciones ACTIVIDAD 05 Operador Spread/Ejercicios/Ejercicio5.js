import { useState } from 'react';
import { Text, Button, TextInput, View, StyleSheet } from 'react-native';

export default function Ejercicio5() {
  const [textDni, setTextDni] = useState('');
  const [textName, setTextName] = useState('');
  const [user, setUser] = useState([{ nombre: 'Nombre', dni: 'Dni' }]);

  const handleOnPress = () => {
    if (validarDni(textDni)) {
      alert('DNI y nombre introducidos correctamente');
      let newArray = [...user];
      newArray.push({ nombre: textName, dni: textDni });
      setUser(newArray);
      setTextName('');
      setTextDni('');
    } else {
      alert('Introduce un DNI correcto');
      setTextDni('');
      setTextName('');
    }
  };

  const validarDni = (textDni) => {
    if (
      textDni !== '' &&
      textDni.length === 9 &&
      isNaN(parseInt(textDni[textDni.length - 1])) &&
      !isNaN(textDni.substring(0, 8))
    ) {
      return true;
    } else {
      return false;
    }
  };

  const users = user.map((value) => (
    <Text>{value.nombre + ' / ' + value.dni}</Text>
  ));

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 30 }}>DNI</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta DNI"
        onChangeText={(newText) => setTextDni(newText)}
        defaultValue={textDni}
      />
      <Text style={{ fontSize: 30 }}>Nombre</Text>
      <TextInput
        style={{ height: 40 }}
        placeholder="Inserta nombre"
        onChangeText={(newName) => setTextName(newName)}
        defaultValue={textName}
      />
      <Button title="Validar" onPress={handleOnPress} />
      {users}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
