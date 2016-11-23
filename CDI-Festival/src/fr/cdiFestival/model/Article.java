package fr.cdiFestival.model;

import fr.cdiFestival.dao.article.RequestId;

/**
 * Article is Used to create and manipulate article
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

	
	/**
	 *Constructor with auto generated ID (usde with the GUI createArticle)
	 */
	public Article (String author, String date, String title, String content) {
		this.idAuto();
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}
	
	/**
	 *Constructor (used with DAO to research a Article in the database)
	 */
	public Article (int id, String author, String date, String title, String content) {
		this.id		 = id; 
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}

	
	/**
	 *idAuto is an id maker.
	 *start a connection with the database, take the last id, do +1 
	 *and give it to the new Article instance.
	 *Update directly the database after the operation.
	 *
	 *@param RequestId
	 */
	public void idAuto() {
		//Initialize RequestId, get the current value and give it to Article ID. Then update the ID in database
		RequestId request = new RequestId();
		id = request.getRefId() + 1;
		request.update(id);
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
	 * @param author
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
	 * @param date
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
	 * @param title
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
	 * @param content
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