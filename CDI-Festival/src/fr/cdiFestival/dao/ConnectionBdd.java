package fr.cdiFestival.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBdd {

	
	//Attributs
	private static final	String 			NOM_DRIVER			= "oracle.jdbc.driver.OracleDriver";
	private	static final	String 			DB_URL_JUNON 		= "jdbc:oracle:thin:stag09/stag09pw@junon:1521:afpa";
//	private static final	String 			DB_URL_LOCALHOST 	= "jdbc:oracle:thin:jonathan/pw@localhost:1521:xe";   
	private					Connection		connection;
	
	public ConnectionBdd() {
	}

	//Méthode d'initialisation de la connection soit à la BDD AFPA soit sur mon PC en LocalHost
	public void initConnectionLocal() throws SQLException {
		if(connection == null|| connection.isClosed()){
			try {
				Class.forName(NOM_DRIVER);
				connection = DriverManager.getConnection(DB_URL_JUNON);
				}
				catch(ClassNotFoundException e){ // 1
					System.err.println("Erreur Appel2Connexion1 : " + e);
				}
				catch (SQLException s){ // 2
					System.err.println("Erreur Appel2Connexion2 : "
							+ s.getSQLState() + " , "
							+ " (" + s + ")" );
				}
		}
	}
		
	//Méthodes de fermeture de la connection et d'ouverture de Requete
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement createStatement() throws SQLException {
		return connection.createStatement();
		
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
}
