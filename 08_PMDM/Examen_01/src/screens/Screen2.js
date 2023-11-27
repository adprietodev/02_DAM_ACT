//Adrián Prieto Villena
import { StatusBar } from "expo-status-bar";
import { Button } from "react-native";
import { StyleSheet, Text, View } from "react-native";

import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { NavigationContainer } from "@react-navigation/native";

import Tab1 from "./Tab1";
import Tab2 from "./Tab2";

const Tab = createBottomTabNavigator();

export default function Screen2() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Tab1" component={Tab1} />
      <Tab.Screen name="Tab2" component={Tab2} />
    </Tab.Navigator>
  );
}
