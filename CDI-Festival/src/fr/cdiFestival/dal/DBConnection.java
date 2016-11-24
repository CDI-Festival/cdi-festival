package fr.cdiFestival.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create a single object Connection to handle a link to the database.
 * @author Claire
 * @version 20161114
 */

public class DBConnection {
	
	// Defines a JDBC driver
	private final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//Defines a connection string
	// TODO (group) ask for a group connection!
	// Claire connection
	private final static String JUNON_URL = "jdbc:oracle:thin:cdifestival/stagpw@junon:1521:AFPA";
	
	// Jean-Luc connection
	
	// Jonathan connection
	
	// Nicolas connection
	
	
	// New connection
	private static Connection connect;
	
	/**
	 * Private constructor to prevent the instantiation of multiple connections.
	 */
	private DBConnection() {
		
	}
	
	/**
	 * Checks if a connection to DB already exists before creating one.
	 * @return connect
	 */
	static {
		try {
			// Loads JDDBC Oracle driver
			Class.forName(ORACLE_DRIVER);
			// Asks for a new connection
			if(connect == null) {
				connect = DriverManager.getConnection(JUNON_URL);
			}
			else {
				System.out.println("Une connexion existe déjà.");
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("Oracle : le driver n'a pas été trouvé.");
		}
		catch (SQLException se) {
			System.err.println("DB - Erreur lors de la connexion : "
				+ se.getSQLState() + " , "
				+ " (" + se + ")" );
		}
	}
	
	/**
	 * This method return a the connection to database.
	 * @return connect
	 */
	public static Connection getConnect() {
		return connect;
	}
}