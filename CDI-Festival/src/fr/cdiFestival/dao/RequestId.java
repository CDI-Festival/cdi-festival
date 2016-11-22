package fr.cdiFestival.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestId {

	private ConnectionBdd		connection;
	private	PreparedStatement 	prepStmt;
	private ResultSet 			result;
	private int					currentId;		
	
	
	//Constructeur
	public RequestId(){
		connection= new ConnectionBdd();
	}
	
	
	//Méthod DLM to update id reference in the table
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
