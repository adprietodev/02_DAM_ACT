import { useState, useRef, useEffect } from 'react';
import { StyleSheet, Image, Button, Text, View, TouchableOpacity } from 'react-native';
import { Camera, CameraType } from 'expo-camera';
import { MaterialIcons } from '@expo/vector-icons';

export default function MobileCamera() {
  const [hasPermission, setHasPermission] = useState(null);
  const [type, setType] = useState(CameraType.back);
  const [photo, setPhoto] = useState();
  const [shooting, setShooting] = useState(false);
  const [cam, setCam] = useState(true);

  const camera = useRef(null);

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === 'granted');
    })();
  }, []);

  useEffect(() => {
    console.log(photo);
  }, [photo]);

  if (hasPermission === null) {
    return <View />;
  }
  if (hasPermission === false) {
    return <Text>No access to camera</Text>;
  }

  const takePicture = async () => {
    const options = { quality: 0.5, base64: true };
    const img = await camera.current.takePictureAsync(options);

    setPhoto(img.uri);
    setShooting(!shooting);
    setCam(!cam);
  };

  return (
    <View style={styles.container}>
      {cam && (
        <View
          style={{
            flex: 1,
            justifyContent: 'center',
            backgroundColor: '#ecf0f1',
            padding: 10,
          }}>
          <Button
            title="Start Shooting"
            onPress={() => [setShooting(!shooting), setCam(!cam)]}
          />
          <Image
            style={{
              width: 370,
              height: 450,
            }}
            source={{
              uri: photo,
            }}
          />
        </View>
      )}
      {shooting && (
        <Camera style={styles.camera} ref={camera} type={type}>
          <View style={styles.buttonContainer}>
            <TouchableOpacity
              style={styles.button}
              onPress={() => {
                setType(
                  type === CameraType.back ? CameraType.front : CameraType.back
                );
              }}>
              <Text style={styles.text}> Flip </Text>
              <MaterialIcons
                onPress={() => takePicture()}
                name="play-circle-outline"
                size={75}
                color="black"
              />
            </TouchableOpacity>
          </View>
        </Camera>
      )}
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
    backgroundColor: 'transparent',
    flexDirection: 'row',
    margin: 20,
  },
  button: {
    flex: 1,
    alignSelf: 'flex-end',
    alignItems: 'center',
  },
  text: {
    fontSize: 18,
    color: 'white',
  },
});
