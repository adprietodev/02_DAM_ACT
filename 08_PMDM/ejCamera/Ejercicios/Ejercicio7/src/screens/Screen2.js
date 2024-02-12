import { useContext, useState, useRef } from 'react';
import ScreensContext from './ScreensContext';
import { View, StyleSheet } from 'react-native';
import { Video } from 'expo-av';


export default function Pantalla2() {
  const { uris, setUris } = useContext(ScreensContext);
  const [status, setStatus] = useState({});

  const camera = useRef(null);


  return (
    <View style={{ justifyContent: 'center', alignItems: 'center' }}>
      {uris.map((value) => (
        <Video
          ref={camera}
          style={styles.video}
          source={{
            uri: value,
          }}
          useNativeControls
          resizeMode="contain"
          onPlaybackStatusUpdate={(status) => setStatus(() => status)}
        />
      ))}
    </View>
  );
}


const styles = StyleSheet.create({
    video: {
    alignSelf: 'center',
    width: 350,
    height: 220,
  }
});