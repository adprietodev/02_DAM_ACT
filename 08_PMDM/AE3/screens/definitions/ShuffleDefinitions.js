const shuffleDefinition = (splitDesc) => {
    if (!Array.isArray(splitDesc) || splitDesc.length === 0) {
        return [];
    }
    const shuffledArray = [...splitDesc];

    for (let i = shuffledArray.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
    }

    return shuffledArray;
};

export default shuffleDefinition;
