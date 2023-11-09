import { TextInput, Text, ScrollView, View } from "react-native";
import { useState } from "react";
import { Center, Box, Heading, VStack, NativeBaseProvider } from "native-base";

export default function App() {
  return (
    <NativeBaseProvider>
      <Center w="100%">
        <Box safeArea p="2" w="90%" maxW="290" py="5">
          <Heading
            size="lg"
            color="coolGray.800"
            _dark={{ color: "warmGray.50" }}
            fontSize="50"
            fontWeight="semibold"
          >
            Crosswords
          </Heading>

          <VStack space={2} mt="5">
            <View style={{ justifyContent: "center", alignItems: "center" }}>
              <View style={{ flexDirection: "row" }}>
                <View style={{ flexDirection: "column" }}>
                  <View style={{ flexDirection: "column" }}>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 134 }}></View>
                      <View style={{ width: 16 }}>
                        <Text style={{ fontSize: 20 }}>1</Text>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                    </View>
                    {/* Linea 2 */}
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 16 }}>
                        <Text style={{ fontSize: 20 }}>2</Text>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 150 }}></View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 54 }}></View>
                      <View style={{ width: 16 }}>
                        <Text style={{ fontSize: 20 }}>3</Text>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
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
                          defaultValue={0}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 134 }}></View>
                      <View style={{ width: 16 }}>
                        <Text style={{ fontSize: 20 }}>4</Text>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
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
                          defaultValue={0}
                        />
                      </View>
                    </View>
                    <View style={{ flexDirection: "row" }}>
                      <View style={{ width: 27 }}></View>
                      <View style={{ width: 16 }}>
                        <Text style={{ fontSize: 20 }}>5</Text>
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
                        />
                      </View>
                      <View style={{ padding: 2, borderWidth: 1 }}>
                        <TextInput
                          placeholder={""}
                          size="20"
                          defaultValue={0}
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
                <Text>1. {""}</Text>
                <Text>2. {""}</Text>
                <Text>3. {""}</Text>
                <Text>4. {""}</Text>
                <Text>5. {""}</Text>
              </ScrollView>
            </View>
          </VStack>
        </Box>
      </Center>
    </NativeBaseProvider>
  );
}
