import { StyleSheet, Button, Text, View } from 'react-native';

const ScreenStack3 = (props) => {
  return (
    <View style={styles.layout}>
      <Text style={styles.title}>Screen Stack 3</Text>
      <Button
        title="Go to Screen Stack 1"
        onPress={() => props.navigation.navigate('ScreenStack1')}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  layout: {
    flex: 1,
    justifyContent: 'center',
    padding: 8,
  },
  title: {
    margin: 24,
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});

export default ScreenStack3;
