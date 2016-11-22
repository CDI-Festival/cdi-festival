package fr.cdiFestival.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * class to create differents type 
 * type : 
 * 0 day 1
 * 1 day 2
 * 2 day 3
 * 
 * 3 days 1-2
 * 5 days 2-3
 * 6 days 1-2-3
 * 
 * @author Administrateur
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

	
	
	
	public Pass(int type, int nombre, int price, ArrayList<LocalDate> dates, String dayType) {
		super();
		this.type = type;
		this.nombre = nombre;
		this.price = price;
		this.dates = dates;
		this.dayType = dayType;
	}




	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public int gettype() {
		return type;
	}
	public void settype(int type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ArrayList<LocalDate> getDate() {
		return dates;
	}
	public void setDate(ArrayList<LocalDate>  dates) {
		this.dates = dates;
	}
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	@Override
	public String toString() {
		return "Pass [type=" + type + ", nombre=" + nombre + ", price=" + price + "]";
	}

	
	
	
	

}
