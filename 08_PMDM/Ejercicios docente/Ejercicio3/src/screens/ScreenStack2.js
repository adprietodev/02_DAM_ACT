import { StyleSheet, Button, Text, View } from 'react-native';

const ScreenStack2 = (props) => {
  return (
    <View style={styles.layout}>
      <Text style={styles.title}>Screen Stack 2</Text>
      <Button
        title="Go to Screen Stack 3"
        onPress={() => props.navigation.navigate('ScreenStack3')}
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

export default ScreenStack2;
