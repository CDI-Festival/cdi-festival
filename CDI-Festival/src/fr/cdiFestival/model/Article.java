package fr.cdiFestival.model;


public class Article {

	
	private int			id;
	private String		author;
	private String		date;
	private	String		title; 
	private String		content;

	public Article (int id, String author, String date, String title, String content) {
		this.id		 = id; 
		this.author	 = author;
		this.date 	 = date;
		this.title	 = title;
		this.content = content;
	}
	
}