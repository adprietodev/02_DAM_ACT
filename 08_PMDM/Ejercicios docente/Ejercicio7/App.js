import { createDrawerNavigator } from '@react-navigation/drawer';
import Screen1 from './src/screens/Screen1';
import Screen2 from './src/screens/Screen2';
import { NavigationContainer } from '@react-navigation/native';

const Drawer = createDrawerNavigator();


const App = () => (
  <NavigationContainer>
  <Drawer.Navigator useLegacyImplementation initialRouteName="Home">
    <Drawer.Screen name="Screen 1" component={Screen1} />
    <Drawer.Screen name="Screen 2" component={Screen2} />
  </Drawer.Navigator>
  </NavigationContainer>
);


export default App;