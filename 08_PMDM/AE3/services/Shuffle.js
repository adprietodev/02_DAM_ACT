import getRandomNum from './RandomNumber';
/**
 * Función que utilizamos para barajar la array que le pasemos, se utiliza con Definitions y Listening.
 * @param {*} array le pasamos una array para barajarla
 * @returns retornamos la array barajada.
 */
const shuffle = (array) => {
    if (!Array.isArray(array) || array.length === 0) {
        return [];
    }
    const shuffledArray = [...array];

    for (let i = shuffledArray.length - 1; i > 0; i--) {
        const j = getRandomNum((i + 1));
        [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
    }

    return shuffledArray;
};

export default shuffle;
