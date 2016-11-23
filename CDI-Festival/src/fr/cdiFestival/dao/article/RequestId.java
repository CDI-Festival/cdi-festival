package fr.cdiFestival.dao.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.cdiFestival.dao.ConnectionBdd;

/**
 *ResquestId is used to make some request into the id table in the database.
 *The purpose is to have not two similar article id
 * @see ConnectionBdd
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
public class RequestId {

	////Class attributes
	private ConnectionBdd		connection;
	private	PreparedStatement 	prepStmt;
	private ResultSet 			result;
	private int					currentId;		
	
	/**
	 *Constructor
	 *
	 *Start an instance of ConnectionBdd
	 * 
	 */
	//Constructeur
	public RequestId(){
		connection= new ConnectionBdd();
	}
	
	
	//Méthod DLM to update id reference in the table
	/**
	 *This method update the reference id
	 * 
	 * @param newId
	 * @return void
	 * 
	 */
	public void update(int newId) {
		currentId = this.getRefId();
		
		try {
			connection.initConnection();
			
			prepStmt = connection.prepareStatement("UPDATE IDARTICLE SET Id = ?");
			prepStmt.setInt(1,newId); 
			prepStmt.executeUpdate();

			if (prepStmt != null)	prepStmt.close();
			if (connection != null)	connection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//Method to get the referencial id in the table ID
	/**
	 *This method update the reference id
	 * 
	 * @return Current id in the database
	 * 
	 */
	public int getRefId() {
		currentId = 0;
			
			try {
				connection.initConnection();
				
				prepStmt = connection.prepareStatement("SELECT id from IDARTICLE");
				result = prepStmt.executeQuery();
				
				while (result.next()) {
					int	id		= result.getInt("Id");
					currentId	= id;			
				}
				
			if (prepStmt != null)	prepStmt.close();
			if (connection != null)	connection.closeConnection();
			} 
				
			catch (SQLException e) {
				e.printStackTrace();
			}
			
	return currentId;
			
	}
	
}
