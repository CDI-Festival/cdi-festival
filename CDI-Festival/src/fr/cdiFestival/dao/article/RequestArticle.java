package fr.cdiFestival.dao.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.cdiFestival.dao.ConnectionBdd;
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

/**
 *ResquestArticle is used to make some request into the article table in the database.
 *The connection is already in there.
 * @see ConnectionBdd
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
public class RequestArticle {

	//Class attributes
	private ConnectionBdd		connection;
	private	PreparedStatement 	prepStmt;
	private ResultSet 			result;
	
	private int			id;
	private String		author;
	private String		date;
	private	String		title; 
	private String		content;
	
	private Article		article;
	private Articles	listArticle;
	
	/**
	 *Constructor
	 *
	 *Start an instance of ConnectionBdd
	 * 
	 */
	public RequestArticle() {
		connection= new ConnectionBdd();
	}
	
	
	/**
	 *This method add a new article in the database.
	 * 
	 * @param Article
	 * @return 
	 * @return boolean
	 * 
	 */
	public boolean add(Article article) {
		//Checking the article value, and the article attributes value
		if (article != null) {
			if ((article.getId() != 0) && (article.getAuthor() != null) && (article.getDate() != null) && (article.getTitle() != null) && (article.getContent() != null)) {
				id 		= article.getId();
				author 	= article.getAuthor().trim();
				date 	= article.getDate().trim();
				title 	= article.getTitle().trim();
				content = article.getContent().trim();
				
				//Starting connection with database
					try {
						connection.initConnection();

						//starting adding request
						prepStmt = connection.prepareStatement("insert into article VALUES (?, ?, ?, ?, ?)");
						prepStmt.setInt(1,id); 
						prepStmt.setString(2, author); 
						prepStmt.setString(3, date);
						prepStmt.setString(4,title); 
						prepStmt.setString(5, content);  
						prepStmt.executeUpdate();
			
						//Request and connection closure
						if (prepStmt != null)	prepStmt.close();
						if (connection != null)	connection.closeConnection();
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//Return true if adding worked
				return true;
			}
		}
		//Return false if adding failed
		return false;		
	}
	
	/**
	 *This method delete an article in the database, with a reference ID
	 * 
	 * @param Article id
	 * @return 
	 * @return boolean
	 * 
	 */
	public boolean delete(int id) {
		//CHecking ID value and initialize article
		if (id != 0) {
			article = this.getArticle(id);
		
				//Checking the article value (if it does exist in the database)
				if (article != null) {
					
					//starting connection
					try {
						connection.initConnection();
						
						//Starting request
						prepStmt = connection.prepareStatement("delete article where id = ?");
						prepStmt.setInt(1, id);
						prepStmt.executeUpdate();	
						
						//Request and connection closure
						if (prepStmt != null)	prepStmt.close();
						if (connection != null)	connection.closeConnection();
						} 
						catch (SQLException e) {
							e.printStackTrace();
						}
					//Return true if deleting worked
					return true;
				}
		}
		//Return false if deleting failed
		return false;
	}
	
	/**
	 *This method get an article in the database with a reference ID
	 * 
	 * @param Article id
	 * @return Article
	 * 
	 */
	public Article getArticle(int id) {
		//CHecking the ID value
		if (id != 0){
			
			//Starting connection with the database
			try {
				connection.initConnection();
			
				//Making the request
				prepStmt = connection.prepareStatement("select id, author, dateC, title, content from article where id = ?");
				prepStmt.setInt(1,id); 
				result = prepStmt.executeQuery();
	
				//Getting the values
				while (result.next()) {
					author		= result.getString("author");
					date		= result.getString("dateC");
					title		= result.getString("title");
					content		= result.getString("content");
				}
					//Checking the values and creating article instance
					if ((id != 0) && (author != null) && (date != null) && (title != null) && (content != null)) {	
							article	= new Article(id, author, date, title, content);
					}
					
					//Request and connection closure
					if (prepStmt != null)	prepStmt.close();
					if (connection != null)	connection.closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return article;
	}	

	/**
	 *This method get all Article in the database table
	 * @see Article, Articles
	 * @return Articles (Article arrayList)
	 * 
	 */
	public Articles getArticles() {
		//Initialization of an arrayList<Article> (Articles class)
		listArticle = new Articles();

		//Staring connection with database
		try {
			connection.initConnection();

			//Making request
			prepStmt 	= connection.prepareStatement("select id, author, dateC, title, content from article");
			result 		= prepStmt.executeQuery();
			
			//Getting values
			while (result.next()) {
				id			= result.getInt("id");
				author		= result.getString("author");
				date		= result.getString("dateC");
				title		= result.getString("title");
				content		= result.getString("content");

				//Checking values and creating article instance
				if ((id != 0) && (author != null) && (date != null) && (title != null) && (content != null)) {	
					article	= new Article(id, author, date, title, content);
				}
				
				//Adding article to the arrayList<Article> (Articles class)
				listArticle.add(article);
			}			
			
			//Request and connection closure
			if (prepStmt != null)	prepStmt.close();
			if (connection != null)	connection.closeConnection();

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return listArticle;
	}
	
	/**
	 *This method update an article in the database.
	 * 
	 * @param Article id
	 * @return boolean
	 * 
	 */
	public boolean upDate (Article article) {	
		//Checking article value and creating a difference Article instance (I need to keep the sent one) to look for if it exist
		if (article != null) {
			Article articleTest = this.getArticle(article.getId());
			
			if (articleTest != null) {
				if ((article.getId() != 0) && (article.getAuthor() != null) && (article.getDate() != null) && (article.getTitle() != null) && (article.getContent() != null)) {
					id 		= article.getId();
					author 	= article.getAuthor().trim();
					date 	= article.getDate().trim();
					title 	= article.getTitle().trim();
					content = article.getContent().trim();
				
					//Starting connection
					try {
						connection.initConnection();
					
						//Making request
						prepStmt = connection.prepareStatement("UPDATE article set author = ?, dateC = ?, title = ?, content = ? where id = ?"); 
						prepStmt.setString(1, author); 
						prepStmt.setString(2, date);
						prepStmt.setString(3,title); 
						prepStmt.setString(4, content);
						prepStmt.setInt(5,id);
						prepStmt.executeUpdate();

						//Request and connection closure
						if (prepStmt != null)	prepStmt.close();
						if (connection != null)	connection.closeConnection();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					return true;
				
				}
			}	
					
		}
		return false;
	}

}