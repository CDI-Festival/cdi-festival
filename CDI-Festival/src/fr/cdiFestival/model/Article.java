package fr.cdiFestival.model;

import fr.cdiFestival.dao.RequestId;

public class Article {

	//Class attributs
	private int			id;
	private String		author;
	private String		date;
	private	String		title; 
	private String		content;

	
	//Constructor with auto generated ID (GUI)
	public Article (String author, String date, String title, String content) {
		this.idAuto();
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}
	
	//DAO constructor (ID already exists)
	public Article (int id, String author, String date, String title, String content) {
		this.id		 = id; 
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}

	
	//Id maker
	public void idAuto() {
		RequestId requete = new RequestId();
		id = requete.getRefId() + 1;
	}
	
	//Getters and setters
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", date=" + date + ", title=" + title + ", content="
				+ content + "]";
	}
}