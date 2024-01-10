import { View } from "react-native";
import { useState, useEffect } from "react";
import {
  Center,
  Box,
  Heading,
  VStack,
  NativeBaseProvider,
  Button,
} from "native-base";
import { Audio } from "expo-av";

export default function App() {
  const sounds = [
    "chh1.wav",
    "chh2.wav",
    "chh3.wav",
    "chh4.wav",
    "dr_tb_1.wav",
    "dr_tb_2.wav",
    "dr_tb_3.wav",
    "dr_tb_4.wav",
    "kk1.wav",
    "kk2.wav",
    "kk3.wav",
    "lo-fi-cow.wav",
    "sn1.wav",
    "sn2.wav",
    "sn3.wav",
    "sn4.wav",
  ];

  const [sound, setSound] = useState(null);

  //Reproducir el audio
  async function playLocalSound(event, index) {
    const { sound } = await Audio.Sound.createAsync(
      require(`./drumkit/${sounds[index]}`)
    );
    setSound(sound); //Aquí es donde lo guardamos.
    await sound.playAsync();
  }

  useEffect(() => {
    return sound
      ? () => {
          console.log("Unloading Sound");
          sound.unloadAsync();
        }
      : undefined;
  }, [sound]);

  return (
    <NativeBaseProvider>
      <Center w="80%">
        <Box safeArea p="2" w="90%" maxW="290" py="5">
          <Heading
            size="lg"
            color="coolGray.800"
            _dark={{ color: "warmGray.50" }}
            fontSize="50"
            fontWeight="semibold"
          >
            Beat Box
          </Heading>

          <VStack space={2} mt="5">
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 0)}
                  size="20"
                  mt="5"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 1)}
                  size="20"
                  mt="5"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 2)}
                  size="20"
                  mt="5"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 3)}
                  size="20"
                  mt="5"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
            </View>
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 4)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 5)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 6)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 7)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
            </View>
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 8)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 9)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 10)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 11)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
            </View>
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 12)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 13)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 14)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
              <View style={{ padding: 2 }}>
                <Button
                  onPress={(event) => playLocalSound(event, 15)}
                  size="20"
                  mt="-1"
                  colorScheme="blue"
                >
                  {" "}
                </Button>
              </View>
            </View>
          </VStack>
        </Box>
      </Center>
    </NativeBaseProvider>
  );
}
