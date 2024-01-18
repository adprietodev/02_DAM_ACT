import { TouchableOpacity, Text, View } from 'react-native';
import synonyms from '../../services/data/synonyms.json';

export default function Synonyms() {
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
          <TouchableOpacity
            style={{
              borderRadius: 8,
              justifyContent: 'center',
              alignItems: 'center',
              textAlignVertical: 'center',
              width: 80,
              height: 80,
              backgroundColor: 'blue',
            }}
          >
            <Text style={{ color: 'white' }}>Hola</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}
