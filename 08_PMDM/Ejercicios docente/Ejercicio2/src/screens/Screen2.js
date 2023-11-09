import { StyleSheet, Button, Text, View } from 'react-native';

const Screen2 = (props) => {
  return (
    <View style={styles.layout}>
      <Text style={styles.title}>Screen 2</Text>
      <Button
        title="Back to Screen 1"
        onPress={() => props.navigation.navigate('Screen1')}
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

export default Screen2;
