package fr.cdiFestival.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * class to create differents pass type 
 * type : 
 * 0 day 1
 * 1 day 2
 * 2 day 3
 * 
 * 3 days 1-2
 * 5 days 2-3
 * 6 days 1-2-3
 * 
 * @author Nicolas Tarral
 * @version : 2016-11-23
 *
 */
public class Pass {
	
	private int type;
	private int nombre;
	private int price;
	
	private ArrayList<LocalDate>  dates;
	private String dayType;
	
	public Pass() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * Constructeur secondaire 
	 * @param type identifie le pass
	 * @param nombre nombre de pass restant
	 * @param price defini son tarif
	 * @param dates defini la date du pass.
	 * @param dayType une chaine de caractère decrivant le pass.
	 */
	public Pass(int type, int nombre, int price, ArrayList<LocalDate> dates, String dayType) {
		super();
		this.type = type;
		this.nombre = nombre;
		this.price = price;
		this.dates = dates;
		this.dayType = dayType;
	}


	/**
	 * methode accesor qui donne le nombre de pass dispoible
	 * @return en entier quantité restante.
	 */
	public int getNombre() {
		return nombre;
	}
	/**
	 * Mutateur permettant de modifier le nombre de place disponible.
	 *
	 * @param nombre entier indquant la nouvelle quantité.
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	/**
	 * methode accesor qui donne le type de pass.
	 * @return en entier le type de pass
	 */
	public int gettype() {
		return type;
	}
	

	
	/**
	 * Acceseur donne le prix du pass
	 * @return un entier represente le prix.
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Mutateur pour changement de prix d'un pass
	 * @param price sera le nouveau prix en entier.
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * methode accesor donnant le ou les dates pour le pass.
	 * @return un ArrayList de LocalDate avec un ou plusieurs date.
	 */
	public ArrayList<LocalDate> getDate() {
		return dates;
	}

	
	

	/**
	 * Accesseur donnant une chaine de caractere decrivant le pass
	 * @return chaine de caractère avec le titre du pass.
	 */
	public String getDayType() {
		return dayType;
	}
	
	/**
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}



	public void setDates(ArrayList<LocalDate> dates) {
		this.dates = dates;
	}



	public void setDayType(String dayType) {
		this.dayType = dayType;
	}



	@Override
	public String toString() {
		return "Pass [type=" + type + ", nombre=" + nombre + ", price=" + price + "]";
	}

	
	
	
	

}
