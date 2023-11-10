import { TextInput, Text, ScrollView, View } from "react-native";
import { useState } from "react";
import { Center, Box, Heading, VStack, NativeBaseProvider } from "native-base";
import { TouchableOpacity } from "react-native";
import axios from "axios";

export default function Crosswords(props) {
  /**
   * Matriz que utilizamos para dibujar el cross y poder comprobar cada letra correctamente
   */
  const [posCellWords, setPosCellWords] = useState([
    [null, null, null, null, null, "", null, null, null, null],
    ["", "", "", "", "", "", "", "", "", null],
    [null, null, null, null, null, "", null, null, null, null],
    [null, null, "", "", "", "", "", "", null, null],
    [null, null, null, null, null, "", null, null, null, null],
    [null, null, null, null, null, "", "", "", null, null],
    [null, null, null, null, null, "", null, null, null, null],
    [null, "", "", "", "", "", "", "", "", ""],
  ]);
  //Array de palabras que corresponden.
  const words = ["software", "developer", "system", "app", "framework"];
  //Array de descripciones de las palabras anteriores.
  const [descWords, setDescWords] = useState([]);
  //Booleano para saber si el juego a comenzado o no
  const [start, setStart] = useState(false);

  //Donde recogemos las descripciones de las palabras llamando a la api y las asignamos en la array, tambien ponemos start a true
  //,ya que es donde llamamos para iniciar el juego.

  const gameData = async () => {
    try {
      const promises = words.map(async (word, i) => {
        const resDescWord = await axios.get(
          `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`
        );
        return resDescWord.data[0].meanings[0].definitions[0].definition;
      });

      const results = await Promise.all(promises);

      setDescWords(results);
      setStart(true);
    } catch (error) {
      console.log(error);
    }
  };

  //Asignamos la letra puesta en el input en la matriz anterior para que se muestre en pantalla.
  const handleTextChange = (text, row, column) => {
    const sumChar = text.length;
    if (sumChar === 1) {
      const updatePosCellWords = [...posCellWords];
      updatePosCellWords[row][column] = text;
      setPosCellWords(updatePosCellWords);
    }
  };

  //Función donde comprobamos que el numero donde hacemos clic las letras concidan en la posición con la palabra.
  const checkWord = (num, pos) => {
    if (start) {
      const lettersWord = words[num - 1].toUpperCase().split("");
      //console.log(lettersWord);
      let count = 0;
      const updatePosCellWords = [...posCellWords];
      if (num === 1) {
        posCellWords.map((row, i) => {
          const letter = row[pos];
          if (letter != null) {
            if (lettersWord[count].toUpperCase() != letter) {
              updatePosCellWords[i][pos] = "";
              setPosCellWords(updatePosCellWords);
            }
            count++;
          }
          return null;
        });
      } else {
        posCellWords[pos].map((dataCol, i) => {
          const letter = dataCol;
          if (letter != null) {
            if (lettersWord[count].toUpperCase() != letter) {
              updatePosCellWords[pos][i] = "";
              setPosCellWords(updatePosCellWords);
            }
            count++;
          }
          return null;
        });
      }
    }
  };

  return (
    <NativeBaseProvider>
      <Center w="100%">
        <Box safeArea p="2" w="90%" maxW="290" py="5">
          <TouchableOpacity onPress={gameData}>
            <Heading
              size="lg"
              color="coolGray.800"
              _dark={{ color: "warmGray.50" }}
              fontSize="50"
              fontWeight="semibold"
            >
              Crosswords
            </Heading>
          </TouchableOpacity>

          <VStack space={2} mt="5">
            <View style={{ justifyContent: "center", alignItems: "center" }}>
              <View style={{ flexDirection: "row" }}>
                <View style={{ flexDirection: "column" }}>
                  <View style={{ flexDirection: "column" }}>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 134 }}></View>
                      <View style={{ width: 16 }}>
                        <TouchableOpacity onPress={() => checkWord(1, 5)}>
                          <Text style={{ fontSize: 20 }}>1</Text>
                        </TouchableOpacity>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 0, 5)}
                          value={posCellWords[0][5]}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 16 }}>
                        <TouchableOpacity onPress={() => checkWord(2, 1)}>
                          <Text style={{ fontSize: 20 }}>2</Text>
                        </TouchableOpacity>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 0)}
                          value={posCellWords[1][0]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 1)}
                          value={posCellWords[1][1]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 2)}
                          value={posCellWords[1][2]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 3)}
                          value={posCellWords[1][3]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 4)}
                          value={posCellWords[1][4]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 5)}
                          value={posCellWords[1][5]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 6)}
                          value={posCellWords[1][6]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 7)}
                          value={posCellWords[1][7]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 1, 8)}
                          value={posCellWords[1][8]}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 150 }}></View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 2, 5)}
                          value={posCellWords[2][5]}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 54 }}></View>
                      <View style={{ width: 16 }}>
                        <TouchableOpacity onPress={() => checkWord(3, 3)}>
                          <Text style={{ fontSize: 20 }}>3</Text>
                        </TouchableOpacity>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 3, 2)}
                          value={posCellWords[3][2]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 3, 3)}
                          value={posCellWords[3][3]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 3, 4)}
                          value={posCellWords[3][4]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 3, 5)}
                          value={posCellWords[3][5]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 3, 6)}
                          value={posCellWords[3][6]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 3, 7)}
                          value={posCellWords[3][7]}
                        />
                      </View>
                      <View style={{ width: 64 }}></View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 150 }}></View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 4, 5)}
                          value={posCellWords[4][5]}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 134 }}></View>
                      <View style={{ width: 16 }}>
                        <TouchableOpacity onPress={() => checkWord(4, 5)}>
                          <Text style={{ fontSize: 20 }}>4</Text>
                        </TouchableOpacity>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 5, 5)}
                          value={posCellWords[5][5]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 5, 6)}
                          value={posCellWords[5][6]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 5, 7)}
                          value={posCellWords[5][7]}
                        />
                      </View>
                      <View style={{ width: 64 }}></View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 150 }}></View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 6, 5)}
                          value={posCellWords[6][5]}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 27 }}></View>
                      <View style={{ width: 16 }}>
                        <TouchableOpacity onPress={() => checkWord(5, 7)}>
                          <Text style={{ fontSize: 20 }}>5</Text>
                        </TouchableOpacity>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 1)}
                          value={posCellWords[7][1]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 2)}
                          value={posCellWords[7][2]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 3)}
                          value={posCellWords[7][3]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 4)}
                          value={posCellWords[7][4]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 5)}
                          value={posCellWords[7][5]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 6)}
                          value={posCellWords[7][6]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 7)}
                          value={posCellWords[7][7]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 8)}
                          value={posCellWords[7][8]}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          onChangeText={(text) => handleTextChange(text, 7, 9)}
                          value={posCellWords[7][9]}
                        />
                      </View>
                    </View>
                  </View>
                </View>
              </View>

              <ScrollView>
                <View style={{ height: 27 }}></View>
                <Heading
                  size="lg"
                  color="coolGray.800"
                  _dark={{ color: "warmGray.50" }}
                  fontSize="30"
                  fontWeight="semibold"
                >
                  Definitions
                </Heading>
                <Text>1. {descWords[0]}</Text>
                <Text>2. {descWords[1]}</Text>
                <Text>3. {descWords[2]}</Text>
                <Text>4. {descWords[3]}</Text>
                <Text>5. {descWords[4]}</Text>
              </ScrollView>
            </View>
          </VStack>
        </Box>
      </Center>
    </NativeBaseProvider>
  );
}
