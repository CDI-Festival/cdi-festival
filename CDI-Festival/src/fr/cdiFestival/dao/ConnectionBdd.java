package fr.cdiFestival.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *ConnectionBdd handle the connection with the database.
 *
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
public class ConnectionBdd {

	
	//Class attributes (different URL to different connections)
	private static final	String 			NOM_DRIVER			= "oracle.jdbc.driver.OracleDriver";
//	private	static final	String 			DB_URL_JUNON 		= "jdbc:oracle:thin:stag09/stag09pw@junon:1521:afpa";
	private static final	String			DB_URL_JUNON_ECFWEB = "jdbc:oracle:thin:cdifestival/stagpw@junon:1521:afpa";
//	private static final	String 			DB_URL_LOCALHOST 	= "jdbc:oracle:thin:jonathan/pw@localhost:1521:xe";   
	private					Connection		connection;
	
	public ConnectionBdd() {
	}

	
	/**
	 *This method initiate a connection with the database, using URL (Class static final attributes)
	 * 
	 */
	public void initConnection() throws SQLException {
		if(connection == null || connection.isClosed()){
			try {
				Class.forName(NOM_DRIVER);
				connection = DriverManager.getConnection(DB_URL_JUNON_ECFWEB);
				}
				catch(ClassNotFoundException e){
					System.err.println("Erreur Appel2Connexion1 : " + e);
				}
				catch (SQLException s){
					System.err.println("Erreur Appel2Connexion2 : "
							+ s.getSQLState() + " , "
							+ " (" + s + ")" );
				}
		}
	}
		
	//Méthodes de fermeture de la connection et d'ouverture de Requete
	/**
	 *This method close the the connection with the database.
	 * 
	 * @param Article
	 * @return void
	 * 
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *Used to initiate requests to the database
	 * 
	 */
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
		
	}

	/**
	 *Used to initiate requests to the database (safer)
	 * 
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
}
