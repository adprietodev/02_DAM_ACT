import { Text, View } from 'react-native';
import { useState } from 'react';
import { Center, Box, Heading, VStack, NativeBaseProvider, Button } from "native-base";

export default function App() {
return (

  <NativeBaseProvider>
    <Center w="80%">
      <Box safeArea p="2" w="90%" maxW="290" py="5">
        <Heading onPress={""} size="lg" color="coolGray.800" _dark={{ color: "warmGray.50" }} fontSize="45" fontWeight="semibold">Guess word</Heading>

        <VStack space={2} mt="5">
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 2 }}>
              <Text style={{width: 50, color: "white", fontSize: 30, backgroundColor: "blue", textAlign: 'center', height: 55}}> {""} </Text>
            </View>
            <View style={{ padding: 2 }}>
              <Text style={{width: 50, color: "white", fontSize: 30, backgroundColor: "blue", textAlign: 'center', height: 55}}> {""} </Text>
            </View>
            <View style={{ padding: 2 }}>
              <Text style={{width: 50, color: "white", fontSize: 30, backgroundColor: "blue", textAlign: 'center', height: 55}}> {""} </Text>
            </View>
            <View style={{ padding: 2 }}>
              <Text style={{width: 50, color: "white", fontSize: 30, backgroundColor: "blue", textAlign: 'center', height: 55}}> {""} </Text>
            </View>
            <View style={{ padding: 2 }}>
              <Text style={{width: 50, color: "white", fontSize: 30, backgroundColor: "blue", textAlign: 'center', height: 55}}> {""} </Text>
            </View>
            <View style={{ padding: 2 }}>
              <Text style={{width: 50, color: "white", fontSize: 30, backgroundColor: "blue", textAlign: 'center', height: 55}}> {""} </Text>
            </View>
          </View>
        </VStack>
        
        <VStack space={2} mt="2">
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 2}}>
              <Button size="55" mt="-1" colorScheme="yellow" style={{width: 320, height: 100}}> {""} </Button>
            </View>
          </View>
        </VStack>
        <VStack space={2} mt="2">
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 2}}>
              <Button size="55" mt="-1" colorScheme="yellow" style={{width: 320, height: 100}}> {""} </Button>
            </View>
          </View>
        </VStack>
        <VStack space={2} mt="2">
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 2}}>
              <Button size="55" mt="-1" colorScheme="yellow" style={{width: 320, height: 100}}> {""} </Button>
            </View>
          </View>
        </VStack>
        <VStack space={2} mt="2">
          <View style={{ flexDirection: "row" }}>
            <View style={{ padding: 2}}>
              <Button size="55" mt="-1" colorScheme="green"> Check </Button>
              <Text style={{width: 350, color: "black", fontSize: 20, height: 55}}> {""} </Text>
            </View>
          </View>
          </VStack>
        
      </Box>
    </Center>
  </NativeBaseProvider>
)
}
