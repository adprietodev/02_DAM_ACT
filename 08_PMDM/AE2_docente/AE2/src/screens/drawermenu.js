import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import { createDrawerNavigator } from "@react-navigation/drawer";

import Home from "./home";
import Crosswords from "./crosswords";
import Quizz from "./quizz";

const Drawer = createDrawerNavigator();

export default function DrawerMenu() {
  return (
    <Drawer.Navigator>
      <Drawer.Screen name="Home" component={Home} />
      <Drawer.Screen name="Crosswords" component={Crosswords} />
      <Drawer.Screen name="Quizz" component={Quizz} />
    </Drawer.Navigator>
  );
}
