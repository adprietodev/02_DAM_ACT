import { StatusBar } from "expo-status-bar";
import { Image, View, StyleSheet, Button } from "react-native";
import { useEffect, useState } from "react";

const Screen2 = () => {
  const [image, setImage] = useState();

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
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <Image style={styles.img} source={{ uri: image }} />
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

export default Screen2;
