import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from '@react-navigation/native';
import Home from './src/screens/Home';
import Screen1 from './src/screens/Screen1';
import Screen2 from './src/screens/Screen2';
import Screen3 from './src/screens/Screen3';

const Tab = createBottomTabNavigator();

const App = () => (
  <NavigationContainer>
    <Tab.Navigator>
      <Tab.Screen name="Home" component={Home} />
      <Tab.Screen name="Screen1" component={Screen1} />
      <Tab.Screen name="Screen2" component={Screen2} />
      <Tab.Screen name="Screen3" component={Screen3} />
    </Tab.Navigator>
  </NavigationContainer>
);

export default App;
