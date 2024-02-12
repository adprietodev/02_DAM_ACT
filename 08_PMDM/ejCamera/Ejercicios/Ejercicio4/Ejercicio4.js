import Screen1 from './src/screens/Screen1';
import Screen2 from './src/screens/Screen2';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';
import { ScreensProvider } from './src/screens/ScreensContext';
import 'react-native-gesture-handler';

const Stack = createStackNavigator();

export default function Ejercicio4() {
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
}
