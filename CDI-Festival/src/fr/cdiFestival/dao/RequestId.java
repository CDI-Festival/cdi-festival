package fr.cdiFestival.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *ResquestId is used to make some request into the id table in the database.
 *The purpose is to have not two similar article id
 * @see ConnectionBdd
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
public class RequestId {

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
	
	
	//M�thod DLM to update id reference in the table
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
			connection.initConnectionLocal();
			
			prepStmt = connection.prepareStatement("UPDATE ID SET current_Id = ?");
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
				connection.initConnectionLocal();
				
				prepStmt = connection.prepareStatement("select current_Id from ID");
				result = prepStmt.executeQuery();
				
				while (result.next()) {
					int	id		= result.getInt("current_Id");
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
