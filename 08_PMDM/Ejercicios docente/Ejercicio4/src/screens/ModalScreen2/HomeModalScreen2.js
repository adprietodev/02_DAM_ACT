import { createStackNavigator } from '@react-navigation/stack';
import ModalScreen1 from './ModalScreen1';
import ModalScreen2 from './ModalScreen2'


const Stack = createStackNavigator();

const HomeModalScreen2 = () => (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Group>
        <Stack.Screen name="ModalScreen1" component={ModalScreen1} options={{ headerShown: false }}/>
      </Stack.Group>
      <Stack.Group screenOptions={{ presentation: 'modal' }}>
        <Stack.Screen name="ModalScreen2" component={ModalScreen2} options={{ headerShown: true, headerMode: 'none' }}/>
      </Stack.Group>
    </Stack.Navigator>
);

export default HomeModalScreen2;