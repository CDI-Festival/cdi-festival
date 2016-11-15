package fr.cdiFestival.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for band creation.
 * 
 * @author Claire
 * @version 20161115
 */
public class Band {
	
	private String name;
	private String biography;
	private String discography;
	private String website;
	private LocalDate date;
	private LocalTime time;
	private String stage;
	
	/**
	 * This constructor constructs a band object without date, time and stage parameters.
	 * 
	 * @param name
	 * @param biography
	 * @param discography
	 * @param website
	 */
	public Band(String name, String biography, String discography, String website) {
		setName(name);
		setBiography(biography);
		setDiscography(discography);
		setWebsite(website);
	}
	
	/**
	 * This constructor constructs a band object with all its parameters.
	 * 
	 * @param name
	 * @param biography
	 * @param discography
	 * @param website
	 * @param date
	 * @param time
	 * @param stage
	 */
	public Band(String name, String biography, String discography, String website, LocalDate date, LocalTime time,
			String stage) {
		setName(name);
		setBiography(biography);
		setDiscography(discography);
		setWebsite(website);
		setDate(date);
		setTime(time);
		setStage(stage);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "band [name=" + name + ", biography=" + biography + ", discography=" + discography + ", website="
				+ website + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}

	/**
	 * @return the discography
	 */
	public String getDiscography() {
		return discography;
	}

	/**
	 * @param discography the discography to set
	 */
	public void setDiscography(String discography) {
		this.discography = discography;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}

}
