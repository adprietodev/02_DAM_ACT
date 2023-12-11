import { StatusBar } from "expo-status-bar";
import { View } from "react-native";

import Screen1 from "./Screen1";
import Screen2 from "./Screen2";

import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";
import { ScreensProvider } from "./ScreensContext";

const Stack = createStackNavigator();

const Eje01 = () => {
  return (
    <ScreensProvider>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen name="Screen1" component={Screen1} />
          <Stack.Screen name="Screen2" component={Screen2} />
        </Stack.Navigator>
      </NavigationContainer>
    </ScreensProvider>
  );
};

export default Eje01;
