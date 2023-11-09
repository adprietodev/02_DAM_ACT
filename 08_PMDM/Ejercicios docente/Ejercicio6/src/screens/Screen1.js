import { createStackNavigator } from '@react-navigation/stack';
import ScreenStack1 from './ScreenStack1';
import ScreenStack2 from './ScreenStack2';

const Stack = createStackNavigator();

const Screen1 = () => {
  return (
    <Stack.Navigator options="false">
      <Stack.Screen name="ScreenStack1" component={ScreenStack1} />
      <Stack.Screen name="ScreenStack2" component={ScreenStack2} />
    </Stack.Navigator>
  );
};

export default Screen1;
