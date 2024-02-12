import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';

import { useState, useEffect, useRef } from 'react';

import { Camera, CameraType } from 'expo-camera';

import { MaterialIcons } from '@expo/vector-icons';

export default function App() {

  const [hasPermission, setHasPermission] = useState(null);
  const [type, setType] = useState(CameraType.back);
  const [photo, setPhoto] = useState();

  const camera = useRef(null);

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === 'granted');
    })();
  }, [])

  if (hasPermission === null) return <View />;
  if (hasPermission === false) return <Text>No acces to camera</Text>;

  const takePicture = async () => {
    const options = { quality: 0.5, base64: true };
    const img = await camera.current.takePictureAsync(options);

    setPhoto(img.uri);
  }

  return (
    <View style={styles.container}>
      <Camera style={styles.camera} ref={camera} type={type}>
        <View style={styles.buttonContainer}>
          <TouchableOpacity
            style={styles.button}
            onPress={() => {
              setType(
                type === CameraType.back ? CameraType.front : CameraType.back
              );
            }} >
            <Text style={styles.text}>Flip</Text>
            <MaterialIcons
              onPress={() => takePicture()}
              name='play-circle-outline'
              size={75}
              color="black"
            />
          </TouchableOpacity>
        </View>
      </Camera>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 50,
  },
  camera: {
    flex: 1,
  },
  buttonContainer: {
    flex: 1,
    backgroundColor: 'trasparent',
    flexDirecction: 'row',
    margin: 20,
  },
  button: {
    flex: 1,
    alingSelf: 'flex-end',
    alingItem: 'center',
  },
  text: {
    fontSize: 18,
    color: 'white',
  }
});
