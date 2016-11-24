package fr.cdiFestival.technic;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.cdiFestival.dal.pass.DaoException;
import fr.cdiFestival.dal.pass.PassDB;
import fr.cdiFestival.model.Pass;
import fr.cdiFestival.service.Passes;




/**
 * Class is going to be use as a buffer class between the servlet and the Dao Class.
 * it is going to old all the pass in an ArrayList.
 * 
 * 
 * * type : 0 day 1 1 day 2 2 day 3
 * 
 * 3 days 1-2 5 days 2-3 6 days 1-2-3
 * 
 * @author nicolas Tarral
 * @version : 2016-11-24
 *
 */
public class StockPass {

	private static final int PLACE_MAXIMUM_JOUR = 1000;
	final float POURCENTAGE_PASS_2_JOUR = 0.20f;
	final float POURCENTAGE_PASS_3_JOUR = 0.15f;
	private static final int YEAR = 2017;
	private static final int MONTH = 06;
	private static final int DAY_1 = 23;
	private static final int DAY_2 = 24;
	private static final int DAY_3 = 25;



	private ArrayList<LocalDate> dates1;
	private ArrayList<LocalDate> dates2;
	private ArrayList<LocalDate> dates3;

	private ArrayList<LocalDate> dates12;
	private ArrayList<LocalDate> dates23;
	private ArrayList<LocalDate> dates123;

	private static Passes allPass;

	private int prixPass1;
	private int prixPass2;
	private int prixPass3;

	private int placeJour1;
	private int placeJour2;
	private int placeJour3;

	private int nbPass2Jour1_2;
	private int nbPass2Jour2_3;

	private int nbPass3Jour1_2_3;

	public StockPass() {

	}

	public StockPass(int prixPass1, int prixPass2, int prixPass3, int placeJour1, int placeJour2, int placeJour3,
			int nbPass2Jour1_2, int nbPass2Jour2_3, int nbPass3Jour1_2_3) {
		super();
		this.prixPass1 = prixPass1;
		this.prixPass2 = prixPass2;
		this.prixPass3 = prixPass3;
		this.placeJour1 = placeJour1;
		this.placeJour2 = placeJour2;
		this.placeJour3 = placeJour3;
		this.nbPass2Jour1_2 = nbPass2Jour1_2;
		this.nbPass2Jour2_3 = nbPass2Jour2_3;
		this.nbPass3Jour1_2_3 = nbPass3Jour1_2_3;
	}

	/**
	 * 
	 * This method is going to generate individuals pass based on multiple
	 * days passes. Those generated passes are going to get stored in the
	 * database.
	 *
	 * 
	 *
	 * @param default parameter is going to indicqte if the pass needs to be auto-generated.
	 */
	public void generateTickets(boolean defaut) {

		
		System.out.println("--- STOCKPASS GenerateTickets ---");
		
		allPass = new Passes();

		dates1 = new ArrayList<LocalDate>();
		dates2 = new ArrayList<LocalDate>();
		dates3 = new ArrayList<LocalDate>();

		dates12 = new ArrayList<LocalDate>();
		dates23 = new ArrayList<LocalDate>();
		dates123 = new ArrayList<LocalDate>();

		dates1.add(LocalDate.of(YEAR, MONTH, DAY_1));
		dates2.add(LocalDate.of(YEAR, MONTH, DAY_2));
		dates3.add(LocalDate.of(YEAR, MONTH, DAY_3));

		dates12.add(LocalDate.of(YEAR, MONTH, DAY_1));
		dates12.add(LocalDate.of(YEAR, MONTH, DAY_2));

		dates23.add(LocalDate.of(YEAR, MONTH, DAY_2));
		dates23.add(LocalDate.of(YEAR, MONTH, DAY_3));

		dates123.add(LocalDate.of(YEAR, MONTH, DAY_1));
		dates123.add(LocalDate.of(YEAR, MONTH, DAY_2));
		dates123.add(LocalDate.of(YEAR, MONTH, DAY_3));

		if (defaut) {

			prixPass1 = 75;
			prixPass2 = 120;
			prixPass3 = 170;

			int nbPass2Jour1_2 = (int) (PLACE_MAXIMUM_JOUR * POURCENTAGE_PASS_2_JOUR);
			int nbPass2Jour2_3 = (int) (PLACE_MAXIMUM_JOUR * POURCENTAGE_PASS_2_JOUR);
			int nbPass3Jour1_2_3 = (int) (PLACE_MAXIMUM_JOUR * POURCENTAGE_PASS_3_JOUR);

			int placeJour1 = PLACE_MAXIMUM_JOUR - (nbPass2Jour1_2 + nbPass3Jour1_2_3); 
																			
			int placeJour2 = PLACE_MAXIMUM_JOUR - (nbPass2Jour1_2 + nbPass2Jour2_3 + nbPass3Jour1_2_3);
																						
			int placeJour3 = PLACE_MAXIMUM_JOUR - (nbPass2Jour2_3 + nbPass3Jour1_2_3); 

			Pass passJour1 = new Pass(0, placeJour1, prixPass1, dates1, "Jour 1");
			Pass passJour2 = new Pass(1, placeJour2, prixPass1, dates2, "Jour 2");
			Pass passJour3 = new Pass(2, placeJour3, prixPass1, dates3, "Jour 3");

			Pass passJour12 = new Pass(3, nbPass2Jour1_2, prixPass2, dates12, "Jour 1 & 2");
			Pass passJour23 = new Pass(5, nbPass2Jour2_3, prixPass2, dates23, "Jour 2 & 3");
			Pass passJour123 = new Pass(6, nbPass3Jour1_2_3, prixPass3, dates123, "Jour 1 & 2 & 3");

			allPass.add(passJour1);
			allPass.add(passJour2);
			allPass.add(passJour3);

			allPass.add(passJour12);
			allPass.add(passJour23);
			allPass.add(passJour123);

		} else {

			Pass passJour1 = new Pass(0, placeJour1, prixPass1, dates1, "Jour 1");
			Pass passJour2 = new Pass(1, placeJour2, prixPass1, dates2, "Jour 2");
			Pass passJour3 = new Pass(2, placeJour3, prixPass1, dates3, "Jour 3");

			Pass passJour12 = new Pass(3, nbPass2Jour1_2, prixPass2, dates12, "Jour 1 & 2");
			Pass passJour23 = new Pass(5, nbPass2Jour2_3, prixPass2, dates23, "Jour 2 & 3");
			Pass passJour123 = new Pass(6, nbPass3Jour1_2_3, prixPass3, dates123, "Jour 1 & 2 & 3");

			allPass.add(passJour1);
			allPass.add(passJour2);
			allPass.add(passJour3);

			allPass.add(passJour12);
			allPass.add(passJour23);
			allPass.add(passJour123);
		}

	}

	/**
	 * This method is going to be used to add new created passes
	 * @throws DaoException 
	 */
	public void storeInDatabase() throws DaoException {

		if (!allPass.isEmpty()) {
			for (Pass current : allPass) {
				PassDB.insertPass(current);
				System.out.println(current.toString());
			}
		} else {
			System.out.println("allpass is empty");
		}

	}

	/**
	 * This methode is going to remove qll pass from the DAO database.
	 * @throws DaoException coming from DB
	 */
	public void emptyTable() throws DaoException {
		PassDB.deleteAllPAss();
	}

	/**
	 * This method is going to call the Dao class to update a pass price.
	 * @throws DaoException coming from DB
	 */
	public void updatePrice() throws DaoException {	
		if (!allPass.isEmpty()) {
			for (Pass current : allPass) {
				PassDB.updatePass(current);
			}
		} else {
			System.out.println("allpass is empty");
		}
		
		
	}

	/**
	 * This methode is going to updqte price for a particual pass.
	 * @param pass objet that is updated.
	 * @throws DaoException coming from DB
	 */
	public void updateQuantity(Pass pass) throws DaoException {
		if (pass != null) {
			PassDB.updatePassQuantity(pass);
		}
	}

	/**
	 * Cette methode soustrait le nombre de type de pass donnée en parametre
	 * 
	 * @param pass quantity to remove.
	 * @return a boolean if the pass quantity was 
	 * @throws DaoException coming from DB
	 */
	public boolean decrementPass(int type, int quantity) throws DaoException {
		
		
		if((type >= 0 && type < 7) || quantity <= 0) {
			return false;
		}
		Pass passType = PassDB.getPass(type);
		int passquantity = passType.getNombre();
		if (passquantity - quantity < passquantity) {
			return false;
		} else {
			passType.setNombre(passquantity - quantity);
			PassDB.updatePassQuantity(passType);
			return true;
		}

	}
	
	public Passes getAllDBPass() throws DaoException {
		allPass = PassDB.getAllPAss();
		return PassDB.getAllPAss();
	}

	public Pass getAPass(int type) throws DaoException {
		Pass pass = new Pass();
		if (type >= 0) {
			pass = PassDB.getPass(type);

		}
		return pass;
	}

	public Passes getAllPass() {

		return allPass;
	}

	public void setAllPass(Passes allPass) {
		this.allPass = allPass;
	}





}
