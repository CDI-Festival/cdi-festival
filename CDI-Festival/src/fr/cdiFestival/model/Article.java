package fr.cdiFestival.model;

import fr.cdiFestival.dao.RequestId;

/**
 * Article is the article fonctionality main class
 * Used to manipulate article
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2016
 */
public class Article {

	//Class attributes
	private int			id;
	private String		author;
	private String		date;
	private	String		title; 
	private String		content;

	
	//Constructor with auto generated ID (GUI)
	/**
	 *Constructor with auto generated ID (use with the GUI createArticle)
	 */
	public Article (String author, String date, String title, String content) {
		this.idAuto();
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}
	
	//DAO constructor (ID already exists)
	/**
	 *Constructor (use with DAO to research a Article in the database)
	 */
	public Article (int id, String author, String date, String title, String content) {
		this.id		 = id; 
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}

	
	//Id maker
	/**
	 *idAuto id an id maker.
	 *start a connection with the database, take the last id, make +1 
	 *and give it to the new Article instance.
	 *Update directly the database after the operation.
	 *@param RequestId
	 *
	 * 
	 */
	public void idAuto() {
		RequestId requete = new RequestId();
		id = requete.getRefId() + 1;
		requete.update(id);
	}
	
	//Getters and setters
	/**
	 * @return The article author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Update The article author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return Creation article date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Update Creation article date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return Article title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Update The article title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Article content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Update the article content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return Article id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return The article description
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", date=" + date + ", title=" + title + ", content="
				+ content + "]";
	}
}