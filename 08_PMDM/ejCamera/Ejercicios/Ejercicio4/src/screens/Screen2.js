import { useContext, useState } from 'react';
import ScreensContext from './ScreensContext';
import { View, Image, Button } from 'react-native';



export default function Screen2() {
    const { uris, setUris } = useContext(ScreensContext);
    const [current, setCurrent] = useState(0)

    const handlePrevious = async () => {
        if(current === 0){
          setCurrent(uris.length - 1);
        } else {
          setCurrent(current - 1)
        }
     }
   
      const handleNext = async () => {
        if(current === uris.length - 1){
          setCurrent(0);
        } else {
          setCurrent(current + 1);
        }
     }

    return (
        <View style={{ justifyContent: 'center', alignItems: 'center' }}>
            <Image
                style={{
                    width: 370,
                    height: 450,
                }}
                source={{
                    uri: uris[current],
                }}
            />
            <View style={{ flexDirection: "row" }}>
                <View style={{ padding: 0 }}>
                    <Button onPress={handlePrevious} title={"Anterior"}></Button>
                    <Button onPress={handleNext} title={"Siguiente"}></Button>
                </View>
            </View>
        </View>
    );
}
