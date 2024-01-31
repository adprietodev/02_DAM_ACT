package es.florida.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;



public class GestorHTTP implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		String requestParamValue = null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange, requestParamValue);
		} else if("POST".equals(httpExchange.getRequestMethod())){
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange, requestParamValue);
		}

	}

	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
	}

	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) {
		try {
			OutputStream outputStream = httpExchange.getResponseBody();
			String htmlResponse = "<html><body>Hola "+requestParamValue+"</body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String handlePostRequest(HttpExchange httpExchange) {
		try {
			InputStream intputStream = httpExchange.getRequestBody();
			//Procesar lo que hay en inputStream, por ejemplo linea a linea y guardalo todo en un string, que será el que devuelva el metodo.
			String postRequest = "";
			
			return postRequest;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) {
		try {
			OutputStream outputStream = httpExchange.getResponseBody();
			String htmlResponse = "Respuesta a la petición POST";
			httpExchange.sendResponseHeaders(201, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
