import { View, Text, Image, StyleSheet } from 'react-native';
import { useState } from 'react';

export default function Ejercicio6() {
  const [content, setContent] = useState([
    {
      title: 'Este es mi titulo 1',
      image:
        'https://media.istockphoto.com/photos/beautiful-sunset-over-the-tropical-sea-picture-id1172427455?k=20&m=1172427455&s=170667a&w=0&h=qU7HPik2cdJKmvN271uOe0U9EcXe-59YN0yaMMkZ8wQ=',
      paragraph: 'bla...',
    },
    {
      title: 'Este es mi titulo 2',
      image:
        'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/17/e0/ce/85/sunset-beach.jpg?w=1200&h=-1&s=1',
      paragraph: 'bla bla bla bla bla bla',
    },
    {
      title: 'Este es mi titulo 3',
      image: 'https://wallpapers.com/images/hd/red-sunset-dadicpsu85pdw66r.jpg',
      paragraph: 'bla bla bla bla bla bla',
    },
  ]);

  const handleOnPress = (index) =>  {
    let myArray = [...content];
    myArray[index].title = 'Titulo cambiado';
    setContent(myArray);
  }

  const Articles = () => {
    return content.map((element, index) => (
      <View style={styles.container}>
        <Text onPress={() => handleOnPress(index)} style={styles.title}>
          {element.title}
        </Text>
        <Image style={styles.image} source={{ uri: element.image }} />
        <Text>{element.paragraph}</Text>
      </View>
    ));
  }

  return <Articles />;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'yellow',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 25,
    fontWeight: 'bold',
    fontStyle: 'italic',
    textDecorationLine: 'underline',
  },
  image: {
    width: 200,
    height: 200,
  },
});
