import { StyleSheet, Text, View } from 'react-native';

const ModalScreen2 = ({ navigation }) => {
   
    return (
        <View style={styles.layout}>
            <Text style={styles.title}>Screen 1</Text>
        </View>
    )
}
const styles = StyleSheet.create({
    layout: {
        justifyContent: 'center',
        alignItems: 'center',
        padding: 8,
    },
    title: {
        margin: 24,
        fontSize: 18,
        fontWeight: 'bold',
        textAlign: 'center',
    }
});

export default ModalScreen2;