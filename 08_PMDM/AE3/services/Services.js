const getData = async (url) => {
  try {
    const response = await fetch(url);
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    return console.log('Error al realizar la petición: ' + error);
  }
};

export default getData;
