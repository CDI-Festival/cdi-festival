package fr.cdiFestival.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

public class RequestArticle {

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
	
	//Constructeur
	public RequestArticle() {
		connection= new ConnectionBdd();
	}
	
	//Méthodes DLM
	public void add(Article article) {
		id 		= article.getId();
		author 	= article.getAuthor();
		date 	= article.getDate();
		title 	= article.getTitle();
		content = article.getContent();
		
		try {
			connection.initConnectionLocal();
			
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
	}
	
	
	public void delete(int id) {
		try {
			connection.initConnectionLocal();
			
			prepStmt = connection.prepareStatement("delete article where id = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();	
			
			if (prepStmt != null)	prepStmt.close();
			if (connection != null)	connection.closeConnection();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Article getArticle(int id) {
		article	= null;
		
		try {
			connection.initConnectionLocal();
			System.out.println("ceci est la connection :" + connection);
			
			prepStmt = connection.prepareStatement("select id, author, dateC, title, content from article where id = ?");
			prepStmt.setInt(1,id); 
			result = prepStmt.executeQuery();

			System.out.println("avant le while : " + id);
			
			while (result.next()) {
				System.out.println("c'est le while");
				author		= result.getString("author");
				System.out.println(author);
				date		= result.getString("dateC");
				System.out.println(date);
				title		= result.getString("title");
				System.out.println(title);
				content		= result.getString("content");
				System.out.println(content);
			}
			
			System.out.println("après le while----");
			System.out.println(id);
			System.out.println(author);
			System.out.println(date);
			System.out.println(title);
			System.out.println(content);
			
			if ((id != 0) && (author != null) && (date != null) && (title != null) && (content != null)) {	
				article	= new Article(id, author, date, title, content);
			}

			if (prepStmt != null)	prepStmt.close();
			if (connection != null)	connection.closeConnection();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return article;
	}	


	public Articles getArticles() {
		listArticle = new Articles();
		article 	= null;

		try {
			connection.initConnectionLocal();

			prepStmt = connection.prepareStatement("select id, author, dateC, title, content from article");

			result = prepStmt.executeQuery();

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
	
	public void upDateArticle (Article article) {
		id 		= article.getId();
		author 	= article.getAuthor();
		date 	= article.getDate();
		title 	= article.getTitle();
		content = article.getContent();

		try {
			connection.initConnectionLocal();

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
	}
}
