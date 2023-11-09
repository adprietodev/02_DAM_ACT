import { StyleSheet, Button, Text, View } from 'react-native';

const Screen1 = (props) => {
  return (
    <View style={styles.layout}>
      <Text style={styles.title}>Screen1</Text>
      <Button
        title="Go to Screen 2"
        onPress={() => props.navigation.navigate('Screen2')}
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

export default Screen1;
