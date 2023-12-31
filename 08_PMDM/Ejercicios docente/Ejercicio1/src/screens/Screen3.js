import { StyleSheet, Text, View } from 'react-native';

const Screen3 = () => {
  return (
    <View style={styles.layout}>
      <Text style={styles.title}>Pantalla 3</Text>
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

export default Screen3;
