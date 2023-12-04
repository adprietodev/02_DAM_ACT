import { StatusBar } from "expo-status-bar";
import { Image, View, StyleSheet, Button, Text } from "react-native";
import { useEffect, useState } from "react";

const Screen3 = () => {
  const [image, setImage] = useState();
  const [countImg, setCountImg] = useState(0);

  useEffect(() => {
    getImage();
  }, []);

  const getImage = async () => {
    try {
      const response = await fetch(
        "https://api.thecatapi.com/v1/images/search?size=full"
      );
      if (response.ok) {
        const res = await response.json();
        setImage(res[0].url);
        setCountImg(countImg + 1);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <Image style={styles.img} source={{ uri: image }} />
      <Text>{countImg}</Text>
      <Button title="Pulsame!" onPress={getImage} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "#ecf0f1",
    padding: 8,
  },
  img: {
    width: "auto",
    height: 300,
  },
});

export default Screen3;
