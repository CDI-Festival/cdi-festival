package fr.cdiFestival.model;

import fr.cdiFestival.dal.band.BandDAO;
import fr.cdiFestival.exceptions.EmptyStringException;
import fr.cdiFestival.exceptions.FiftyCharException;
import fr.cdiFestival.util.ControlMethod;

/**
 * Class for band creation.
 * 
 * @author Claire
 * @version 20161115
 */
public class Band {
	
	private int idDB;
	
	// ECF : creation simple d'un objet groupe pour l'instant. La discographie et les liens avec le module Scene sont a venir.
	private int id;
	private String name;
	private String biography;
//	private String discography;
	private String website;
//	private LocalDate date;
//	private LocalTime time;
//	private String stage;
	
	/**
	 * This constructor constructs a band object without id.
	 * 
	 * @param name
	 * @param biography
	 * @param website
	 */
	public Band(String name, String biography, String website) throws EmptyStringException, FiftyCharException {
		
		setId();
		setName(name);
		setBiography(biography);
		setWebsite(website);
	}
		
	/**
	 * This constructor constructs a band with an id (to handle result from database).
	 * 
	 * @param id
	 * @param name
	 * @param biography
	 * @param website
	 */
	public Band(int id, String name, String biography, String website) throws EmptyStringException, FiftyCharException {
		
		this.id = id;
		setName(name);
		setBiography(biography);
		setWebsite(website);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "band [name=" + name + ", biography=" + biography + ", website=" + website + "]";
	}

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId() {
		BandDAO bandDAO = new BandDAO();
		idDB = bandDAO.getBiggerId();
		idDB++;
		this.id = idDB;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 * @throws EmptyStringException 
	 * @throws FiftyCharException 
	 */
	public void setName(String name) throws EmptyStringException, FiftyCharException {
		
		String bandName;
		bandName = name.trim();	
		
		if (ControlMethod.isEmptyOrNull(bandName)) {
			System.out.println("Band, setName : empty."); // TEST CODE
			throw new EmptyStringException("Le nom du groupe doit être renseigné.");
		}
		else if (ControlMethod.isSup50(bandName)) {
			System.out.println("Band, setName : too long."); // TEST CODE
			throw new FiftyCharException("Le nom du groupe ne peut pas dépasser cinquante caractères.");
		}
		else {
			this.name = bandName;
		}	
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

//	/**
//	 * @return the discography
//	 */
//	public String getDiscography() {
//		return discography;
//	}
//
//	/**
//	 * @param discography the discography to set
//	 */
//	public void setDiscography(String discography) {
//		this.discography = discography;
//	}

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

//	/**
//	 * @return the date
//	 */
//	public LocalDate getDate() {
//		return date;
//	}
//
//	/**
//	 * @param date the date to set
//	 */
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
//
//	/**
//	 * @return the time
//	 */
//	public LocalTime getTime() {
//		return time;
//	}
//
//	/**
//	 * @param time the time to set
//	 */
//	public void setTime(LocalTime time) {
//		this.time = time;
//	}
//
//	/**
//	 * @return the stage
//	 */
//	public String getStage() {
//		return stage;
//	}
//
//	/**
//	 * @param stage the stage to set
//	 */
//	public void setStage(String stage) {
//		this.stage = stage;
//	}

}
