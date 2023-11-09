import { useNavigation } from '@react-navigation/native';
import { StyleSheet, Text, View, Button } from 'react-native';

const HomeModalScreen1 = () => {
    const navigation = useNavigation();
    return (
        <View style={styles.layout}>
            <Text style={styles.title}>Screen 2</Text>
            <View style={styles.box}>
                <Button
                    onPress={() => navigation.navigate('ModalScreen2')}
                    title="Go to Screen 2"
                    style={styles.button}
                />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    layout: {
        justifyContent: 'center',
        alignItems: 'center',
        padding: 8,
        marginTop: 20,
    },
    title: {
        margin: 24,
        fontSize: 18,
        fontWeight: 'bold',
        textAlign: 'center',
    },
    button: {
        marginTop: 20
    }, 
    box: {
        marginBottom: 75
    }
});

export default HomeModalScreen1;