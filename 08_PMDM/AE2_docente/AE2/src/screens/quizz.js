import { TextInput, Text, ScrollView, View } from "react-native";
import { useState } from "react";
import {
  Center,
  Box,
  Heading,
  VStack,
  NativeBaseProvider,
  Button,
} from "native-base";
import { TouchableOpacity } from "react-native";
import axios from "axios";

export default function Quizz() {
  const [descWords, setDescWords] = useState([]);
  const [result, setResult] = useState("");
  const words = ["coding", "mobile", "phones"];
  const [currentWord, setCurrentWord] = useState([]);
  const [count, setCount] = useState(0);
  const [descSelected, setDescSelected] = useState("");

  //Iniciamos el juego y asignamos las descripciones a la array.
  const getWordsDesc = async () => {
    const promises = words.map(async (word, i) => {
      const resDescWord = await axios.get(
        `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
      );
      return resDescWord.data[0].meanings[0].definitions[0].definition;
    });

    const results = await Promise.all(promises);

    setDescWords(results);
    setCurrentWord(words[count].split(""));
    setCount(count + 1);
  };

  //Pasamos de palabra si es correcta
  const nextWord = () => {
    if (count === 3) {
      setResult("¡Juego terminado!");
    } else {
      setCurrentWord(words[count].split(""));
      setCount(count + 1);
    }
  };

  //Comparamos la descripción elegida con la de la palabra llamando a la api
  const comparateDesc = async (desc) => {
    const word = currentWord.join("");
    const res = await axios.get(
      `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
    );
    const descCurrentWord = res.data[0].meanings[0].definitions[0].definition;
    if (desc === descCurrentWord) {
      setResult("¡Correcto!");
      nextWord();
    } else {
      setResult("¡Incorrecto!");
    }
  };

  return (
    <NativeBaseProvider>
      <Center w="80%">
        <Box safeArea p="2" w="90%" maxW="290" py="5">
          <TouchableOpacity onPress={getWordsDesc}>
            <Heading
              size="lg"
              color="coolGray.800"
              _dark={{ color: "warmGray.50" }}
              fontSize="45"
              fontWeight="semibold"
            >
              Guess word
            </Heading>
          </TouchableOpacity>

          <VStack space={2} mt="5">
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Text
                  style={{
                    width: 50,
                    color: "white",
                    fontSize: 30,
                    backgroundColor: "blue",
                    textAlign: "center",
                    height: 55,
                  }}
                >
                  {" "}
                  {currentWord[0]}{" "}
                </Text>
              </View>
              <View style={{ padding: 2 }}>
                <Text
                  style={{
                    width: 50,
                    color: "white",
                    fontSize: 30,
                    backgroundColor: "blue",
                    textAlign: "center",
                    height: 55,
                  }}
                >
                  {" "}
                  {currentWord[1]}{" "}
                </Text>
              </View>
              <View style={{ padding: 2 }}>
                <Text
                  style={{
                    width: 50,
                    color: "white",
                    fontSize: 30,
                    backgroundColor: "blue",
                    textAlign: "center",
                    height: 55,
                  }}
                >
                  {" "}
                  {currentWord[2]}{" "}
                </Text>
              </View>
              <View style={{ padding: 2 }}>
                <Text
                  style={{
                    width: 50,
                    color: "white",
                    fontSize: 30,
                    backgroundColor: "blue",
                    textAlign: "center",
                    height: 55,
                  }}
                >
                  {" "}
                  {currentWord[3]}{" "}
                </Text>
              </View>
              <View style={{ padding: 2 }}>
                <Text
                  style={{
                    width: 50,
                    color: "white",
                    fontSize: 30,
                    backgroundColor: "blue",
                    textAlign: "center",
                    height: 55,
                  }}
                >
                  {" "}
                  {currentWord[4]}{" "}
                </Text>
              </View>
              <View style={{ padding: 2 }}>
                <Text
                  style={{
                    width: 50,
                    color: "white",
                    fontSize: 30,
                    backgroundColor: "blue",
                    textAlign: "center",
                    height: 55,
                  }}
                >
                  {" "}
                  {currentWord[5]}{" "}
                </Text>
              </View>
            </View>
          </VStack>

          <VStack space={2} mt="2">
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  size="55"
                  mt="-1"
                  colorScheme="yellow"
                  style={{ width: 320, height: 100 }}
                  onPress={() => setDescSelected(descWords[2])}
                >
                  {" "}
                  {descWords[2]}{" "}
                </Button>
              </View>
            </View>
          </VStack>
          <VStack space={2} mt="2">
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  size="55"
                  mt="-1"
                  colorScheme="yellow"
                  style={{ width: 320, height: 100 }}
                  onPress={() => setDescSelected(descWords[0])}
                >
                  {" "}
                  {descWords[0]}{" "}
                </Button>
              </View>
            </View>
          </VStack>
          <VStack space={2} mt="2">
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  size="55"
                  mt="-1"
                  colorScheme="yellow"
                  style={{ width: 320, height: 100 }}
                  onPress={() => setDescSelected(descWords[1])}
                >
                  {" "}
                  {descWords[1]}{" "}
                </Button>
              </View>
            </View>
          </VStack>
          <VStack space={2} mt="2">
            <View style={{ flexDirection: "row" }}>
              <View style={{ padding: 2 }}>
                <Button
                  size="55"
                  mt="-1"
                  colorScheme="green"
                  onPress={() => comparateDesc(descSelected)}
                >
                  {" "}
                  Check{" "}
                </Button>
                <Text
                  style={{
                    width: 350,
                    color: "black",
                    fontSize: 20,
                    height: 55,
                  }}
                >
                  {" "}
                  {result}{" "}
                </Text>
              </View>
            </View>
          </VStack>
        </Box>
      </Center>
    </NativeBaseProvider>
  );
}
