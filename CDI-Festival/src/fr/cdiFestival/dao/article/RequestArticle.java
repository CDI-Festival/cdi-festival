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

	////Class attributes
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
	//Constructor
	public RequestArticle() {
		connection= new ConnectionBdd();
	}
	
	//M�thodes DLM
	
	/**
	 *This method add a new article in the database.
	 * 
	 * @param Article
	 * @return 
	 * @return boolean
	 * 
	 */
	public boolean add(Article article) {	
		if (article != null) {
			if ((article.getId() != 0) && (article.getAuthor() != null) && (article.getDate() != null) && (article.getTitle() != null) && (article.getContent() != null)) {
				id 		= article.getId();
				author 	= article.getAuthor().trim();
				date 	= article.getDate().trim();
				title 	= article.getTitle().trim();
				content = article.getContent().trim();
				
					try {
						connection.initConnection();

						prepStmt = connection.prepareStatement("insert into article VALUES (?, ?, ?, ?, ?)");
						prepStmt.setInt(1,id); 
						prepStmt.setString(2, author); 
						prepStmt.setString(3, date);
						prepStmt.setString(4,title); 
						prepStmt.setString(5, content);  
						prepStmt.executeUpdate();
			
						if (prepStmt != null)	prepStmt.close();
						if (connection != null)	connection.closeConnection();
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			}
		}	
		return false;		
	}
	
	/**
	 *This method delete an article, with an id, from database.
	 * 
	 * @param Article id
	 * @return 
	 * @return boolean
	 * 
	 */
	public boolean delete(int id) {
		if (id != 0) {
			article = this.getArticle(id);
		
				if (article != null) {
					try {
						connection.initConnection();
			
						prepStmt = connection.prepareStatement("delete article where id = ?");
						prepStmt.setInt(1, id);
						prepStmt.executeUpdate();	
			
						if (prepStmt != null)	prepStmt.close();
						if (connection != null)	connection.closeConnection();
						} 
						catch (SQLException e) {
							e.printStackTrace();
						}
					return true;
				}
		}
		return false;
	}
	
	/**
	 *This method get an article, with an id, from database.
	 * 
	 * @param Article id
	 * @return Article
	 * 
	 */
	public Article getArticle(int id) {
		if (id != 0){
			try {
				connection.initConnection();
			
				prepStmt = connection.prepareStatement("select id, author, dateC, title, content from article where id = ?");
				prepStmt.setInt(1,id); 
				result = prepStmt.executeQuery();
	
				while (result.next()) {
					author		= result.getString("author");
					date		= result.getString("dateC");
					title		= result.getString("title");
					content		= result.getString("content");
				}
			
					if ((id != 0) && (author != null) && (date != null) && (title != null) && (content != null)) {	
							article	= new Article(id, author, date, title, content);
					}

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
	 *This method get all Article in the table (list of article)
	 * 
	 * @return Articles
	 * 
	 */
	public Articles getArticles() {
		listArticle = new Articles();

		try {
			connection.initConnection();

			prepStmt 	= connection.prepareStatement("select id, author, dateC, title, content from article");
			result 		= prepStmt.executeQuery();

			while (result.next()) {
				id			= result.getInt("id");
				author		= result.getString("author");
				date		= result.getString("dateC");
				title		= result.getString("title");
				content		= result.getString("content");

				if ((id != 0) && (author != null) && (date != null) && (title != null) && (content != null)) {	
					article	= new Article(id, author, date, title, content);
				}

				listArticle.add(article);
			}			

			if (prepStmt != null)	prepStmt.close();
			if (connection != null)	connection.closeConnection();

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return listArticle;
	}
	
	/**
	 *This method update an article, with another one, from database.
	 * 
	 * @param Article id
	 * @return 
	 * @return boolean
	 * 
	 */
	public boolean upDate (Article article) {	
		if (article != null) {
//			if ()
			if ((article.getId() != 0) && (article.getAuthor() != null) && (article.getDate() != null) && (article.getTitle() != null) && (article.getContent() != null)) {
				id 		= article.getId();
				author 	= article.getAuthor().trim();
				date 	= article.getDate().trim();
				title 	= article.getTitle().trim();
				content = article.getContent().trim();
				
				try {
					connection.initConnection();

					prepStmt = connection.prepareStatement("UPDATE article set author = ?, dateC = ?, title = ?, content = ? where id = ?"); 
					prepStmt.setString(1, author); 
					prepStmt.setString(2, date);
					prepStmt.setString(3,title); 
					prepStmt.setString(4, content);
					prepStmt.setInt(5,id);
					prepStmt.executeUpdate();

					if (prepStmt != null)	prepStmt.close();
					if (connection != null)	connection.closeConnection();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			}
		}	
		return false;		
	}

}