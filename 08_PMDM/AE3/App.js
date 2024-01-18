import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from '@react-navigation/native';

import Definitions from './screens/definitions/Definitions';
import FillInTheGaps from './screens/fillInTheGaps/FillInTheGaps';
import Listening from './screens/listening/Listening';
import Synonyms from './screens/synonyms/Synonyms';

const Tab = createBottomTabNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="Definitions" component={Definitions} />
        <Tab.Screen name="Listening" component={Listening} />
        <Tab.Screen name="FillInTheGarps" component={FillInTheGaps} />
        <Tab.Screen name="Synonyms" component={Synonyms} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default App;
