import { Audio } from "expo-av";
/**
 * Función que utilizamos para reproducir el audio
 * @param {*} audio el audio que queremos reproducir.
 */
const playAudio = async (audio) => {
    const { sound } = await Audio.Sound.createAsync({ uri: audio });
    await sound.playAsync();
};

export default playAudio;