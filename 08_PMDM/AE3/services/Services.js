/**
 * Función que utilizamos para coger datos de la api que queramos.
 * @param {*} url le pasamos el url de la api
 * @returns devolvemos el JSON de la respuesta o el error en caso de que haya
 */
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


