package es.florida.model;

public class DataConnection {

	private String ip;
	private int port;
	private String database;
	private String[] collections;
	
	public DataConnection() {
		
	}
	
	public DataConnection(String ip, int port, String database, String[] collections) {
		this.ip = ip;
		this.port = port;
		this.database = database;
		this.collections = collections;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @return the collections
	 */
	public String[] getCollections() {
		return collections;
	}
	
	
}
