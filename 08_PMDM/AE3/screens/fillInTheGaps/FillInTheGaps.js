import { TouchableOpacity, Text, TextInput, View } from 'react-native';
import fillInTheGaps from '../../services/data/fill_in_the_gaps.json';

export default function FillInTheGaps() {
  return (
    <View
      style={{
        justifyContent: 'center',
        alignSelf: 'center',
        marginVertical: 80,
      }}
    >
      <View style={{ flexDirection: 'row' }}>
        <View style={{ padding: 2 }}>
          <Text style={{ fontSize: 20, color: 'black' }}>Hola</Text>
        </View>
      </View>
      <TextInput
        style={{
          color: 'white',
          borderRadius: 8,
          justifyContent: 'center',
          alignItems: 'center',
          textAlignVertical: 'center',
          backgroundColor: 'black',
          height: 80,
        }}
        value={'Hola'}
      />
      <TouchableOpacity
        style={{
          borderRadius: 8,
          justifyContent: 'center',
          alignItems: 'center',
          textAlignVertical: 'center',
          backgroundColor: 'black',
          height: 80,
        }}
      >
        <Text style={{ fontSize: 20, color: 'white' }}>Check!</Text>
      </TouchableOpacity>
    </View>
  );
}
