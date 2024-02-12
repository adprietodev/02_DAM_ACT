import { useContext } from 'react';
import ScreensContext from './ScreensContext';
import { View, Image } from 'react-native';

export default function Screen2() {
  const { uris, setUris } = useContext(ScreensContext);

  return (
    <View style={{ justifyContent: 'center', alignItems: 'center' }}>
      {uris.map((value, index) => (
        <Image
          key={index.toString()}
          style={{
            width: 150,
            height: 150,
          }}
          source={{
            uri: value,
          }}
        />
      ))}
    </View>
  );
}
