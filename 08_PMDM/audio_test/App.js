import { View } from "react-native";
import { useState, useEffect } from "react";
import { Audio } from "expo-av";

import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";

import { AudioProvider } from "./components/AudioContext";

import Home from "./screens/Home";
import Ej01 from "./screens/Ej01";
import Ej03 from "./screens/Ej03";
import Playing from "./screens/Playing";
import Ej04 from "./screens/Ej04";
import EjRec01 from "./screens/EjRec01";
import EjRec02 from "./screens/EjRec02";
import RecordingContext from "./components/RecordingContext";

const Stack = createStackNavigator();

export default function App() {
  return (
    <AudioProvider>
      <NavigationContainer>
        <Stack.Navigator options="false">
          <Stack.Screen name="Home" component={Home} />
          <Stack.Screen name="Ej01" component={Ej01} />
          <Stack.Screen name="Ej03" component={Ej03} />
          <Stack.Screen name="Ej04" component={Ej04} />
          <Stack.Screen name="EjRec01" component={EjRec01} />
          <Stack.Screen name="EjRec02" component={EjRec02} />
          <Stack.Screen name="Playing" component={Playing} />
        </Stack.Navigator>
      </NavigationContainer>
    </AudioProvider>
  );
}
