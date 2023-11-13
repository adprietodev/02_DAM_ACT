import { Text, TouchableOpacity, Image, View, StyleSheet } from 'react-native';
import { useState } from 'react';


export default function Ejercicio12() {
  const [image, setImage] = useState({image: require('../assets/snack-icon.png'), text:'Image one'});
  const [imageTwo, setImageTwo] = useState({image: require('../assets/icon.png'), text:'Image one'});

  function handleOnImage(num) {
      if (num === 0 && image.text === 'Image one') {
        setImage({image: require('../assets/icon.png'), text:'Image two'});
      } else if (num === 0 && image.text === 'Image two') {
        setImage({image: require('../assets/snack-icon.png'), text:'Image one'});
      } else if (num === 1 && imageTwo.text === 'Image one') {
        setImageTwo({image: require('../assets/snack-icon.png'), text:'Image two'});
      } else if (num === 1 && imageTwo.text === 'Image two') {
        setImageTwo({image: require('../assets/icon.png'), text:'Image one'});
      } 
  }

  return (
    <View style={styles.containerRow}>
      <TouchableOpacity onPress={() => handleOnImage(0)}>
        <Image style={styles.image} source={image.image} />
        <Text>{image.text}</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => handleOnImage(1)}>
        <Image style={styles.image} source={imageTwo.image} />
        <Text>{imageTwo.text}</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  containerRow: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection:'row',
  },
  image: { 
    width: 100, 
    height: 100 
  },
});


