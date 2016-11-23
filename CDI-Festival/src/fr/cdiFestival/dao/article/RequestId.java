package fr.cdiFestival.dao.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.cdiFestival.dao.ConnectionBdd;

/**
 *ResquestArticle is used to make some request into the article table in the database.
 *The purpose is to have not two similar article ID.
 *The connection is already in there.
 *
 * @see ConnectionBdd, Article
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
public class RequestId {

	//Class attributes
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
	public RequestId(){
		connection= new ConnectionBdd();
	}
	
	
	/**
	 *This method update the reference id
	 * 
	 * @param newId
	 * @return 
	 * @return boolean
	 * 
	 */
	public boolean update(int newId) {
		//Checking ID value and getting the current ID in the database
		if (newId != 0) {
			currentId = this.getRefId();
		
			//Starting connection
			try {
				connection.initConnection();
			
				//Making request
				prepStmt = connection.prepareStatement("UPDATE IDARTICLE SET Id = ?");
				prepStmt.setInt(1,newId); 
				prepStmt.executeUpdate();

				//Request and connection closure
				if (prepStmt != null)	prepStmt.close();
				if (connection != null)	connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
		
	//Method to get the reference id in the table ID
	/**
	 *This method update the reference id
	 * 
	 * @return Current id in the database
	 * 
	 */
	public int getRefId() {
		currentId = 0;
			
			//Starting connection and making request
			try {
				connection.initConnection();
				
				prepStmt = connection.prepareStatement("SELECT id from IDARTICLE");
				result = prepStmt.executeQuery();
				
				//Getting value to the table
				while (result.next()) {
					int	id		= result.getInt("Id");
					
					//Checking current ID value
					if (id != 0) currentId	= id;			
				}
				
				//Request and connection closure
				if (prepStmt != null)	prepStmt.close();
				if (connection != null)	connection.closeConnection();
			} 	
			catch (SQLException e) {
				e.printStackTrace();
			}
			
	return currentId;
			
	}
	
}
