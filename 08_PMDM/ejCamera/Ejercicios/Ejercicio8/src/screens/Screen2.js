import { useContext, useEffect, useState, useRef } from 'react';
import ScreensContext from './ScreensContext';
import { View, StyleSheet, Button } from 'react-native';
import { Video } from 'expo-av';

export default function Screen2({ navigation }) {
  const { uris, setUris } = useContext(ScreensContext);
  const [status, setStatus] = useState({});
  const [current, setCurrent] = useState(0);

  useEffect(()=> {
    console.log(uris);
  }, [])

  const handlePrevious = async () => {
    if (current === 0) {
      setCurrent(uris.length - 1);
    } else {
      setCurrent(current - 1);
    }
  };

  const handleNext = async () => {
    if (current === uris.length - 1) {
      setCurrent(0);
    } else {
      setCurrent(current + 1);
    }
  };

  const camera = useRef(null);

  return (
    <View style={{ justifyContent: 'center', alignItems: 'center' }}>
      <Video
        ref={camera}
        style={styles.video}
        source={{
          uri: uris[current],
        }}
        useNativeControls
        resizeMode="contain"
        onPlaybackStatusUpdate={(status) => setStatus(() => status)}
      />
      <View style={{ flexDirection: 'row' }}>
        <View style={{ padding: 0 }}>
          <Button onPress={handlePrevious} title={'Anterior'}></Button>
          <Button onPress={handleNext} title={'Siguiente'}></Button>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  video: {
    alignSelf: 'center',
    width: 350,
    height: 220,
  },
});
