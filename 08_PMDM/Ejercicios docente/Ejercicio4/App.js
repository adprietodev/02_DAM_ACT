import { createDrawerNavigator } from '@react-navigation/drawer';
import HomeModalScreen1 from './src/screens/ModalScreen1/HomeModalScreen1';
import HomeModalScreen2 from './src/screens/ModalScreen2/HomeModalScreen2'
import { NavigationContainer } from '@react-navigation/native';

const Drawer = createDrawerNavigator();


const App = () => (
  <NavigationContainer>
  <Drawer.Navigator useLegacyImplementation initialRouteName="Home">
    <Drawer.Screen name="Screen 1" component={HomeModalScreen1} />
    <Drawer.Screen name="Screen 2" component={HomeModalScreen2} />
  </Drawer.Navigator>
  </NavigationContainer>
);


export default App;

