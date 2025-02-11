import { useState, useRef, useEffect } from 'react';
import {
  StyleSheet,
  Image,
  Button,
  Text,
  View,
  TouchableOpacity,
} from 'react-native';
import { Camera, CameraType } from 'expo-camera';
import { Video } from 'expo-av';
import { MaterialIcons } from '@expo/vector-icons';
import { Entypo } from '@expo/vector-icons';


export default function Ejercicio5() {
  const [hasPermission, setHasPermission] = useState(null);
  const [hasAudioPermission, setHasAudioPermission] = useState(null);
  const [type, setType] = useState(CameraType.back);
  const [video, setVideo] = useState();
  const [shooting, setShooting] = useState(false);
  const [cam, setCam] = useState(true);
  const [status, setStatus] = useState({});


  const camera = useRef(null);

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === 'granted');
      const audioStatus = await Camera.requestMicrophonePermissionsAsync();
      setHasAudioPermission(audioStatus.status === 'granted');
    })();
  }, []);


  if (hasPermission === null || hasAudioPermission === null) {
    return <View />;
  }
  if (hasPermission === false || hasAudioPermission === false) {
    return <Text>No access to camera</Text>;
  }

const takeVideo = async () => {
    if(camera){
        const data = await camera.current.recordAsync()
        setVideo(data.uri);
        console.log(data.uri);
    }
  }

const stopVideo = async () => {
    camera.current.stopRecording();
    setCam(!cam);
    setShooting(!shooting);
  }

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
          <Video
            ref={camera}
            style={styles.video}
            source={{
              uri: video,
            }}
            useNativeControls
            resizeMode="contain"
            onPlaybackStatusUpdate={status => setStatus(() => status)}
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
                onPress={() => takeVideo()}
                name="play-circle-outline"
                size={75}
                color="black"
              />
              <Entypo
                onPress={() => stopVideo()}
                name="controller-stop"
                size={90}
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
    video: {
    alignSelf: 'center',
    width: 350,
    height: 220,
  },
});
