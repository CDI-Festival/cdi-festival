package fr.cdiFestival.dal.band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdiFestival.dal.DBConnection;
import fr.cdiFestival.exceptions.EmptyStringException;
import fr.cdiFestival.exceptions.FiftyCharException;
import fr.cdiFestival.model.Band;
import fr.cdiFestival.util.ControlMethod;

/**
 * Handle the SQL request for the BAND table in database.
 * 
 * @author Claire
 * @version 20161123
 */
public class BandDAO {

	// Attributes to handle connection and request to database.
	private Connection connection;
	private int result;
	private ResultSet resultSet;
	
	private PreparedStatement searchBandName;
	private PreparedStatement createBand;
	private PreparedStatement readAllId;
	private PreparedStatement readAllBandName;
	private PreparedStatement updateBand;
	private PreparedStatement deleteBand;
	private PreparedStatement checkBandName;
	
	// Attributes to handle Band object.
	private Band band;
	
	private ArrayList<Integer> idList;
	private int biggerId;
	private ArrayList<String> bandNameList;
	
	private int id;
	private String bandName;
	private String bandBiography;
	private String bandWebsite;
	
	/**
	 * Asks for the connection to database.
	 */
	public BandDAO() {
		this.connection = DBConnection.getConnect();
	}
	
	/**
	 * This methods searches the name in parameter in database and return all its informations.
	 * 
	 * @author Claire
	 * @param band_name
	 * @return band
	 * @version 20161119
	 */
	public Band search(String name) {
		
		try {
			searchBandName = connection.prepareStatement(SQLRequest.SELECT_WHERE_BANDNAME);
			searchBandName.setString(1, name);
			
			resultSet = searchBandName.executeQuery();			
			while (resultSet.next()) {
				bandName = resultSet.getString(1);
				bandBiography = resultSet.getString(2);
				bandWebsite = resultSet.getString(3);
				try {
					band = new Band(bandName, bandBiography, bandWebsite);
				} catch (EmptyStringException | FiftyCharException e) {
					// TODO (Claire) DAO gerer exception Band (search)
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : la recherche n'a pas pu �tre ex�cut�e."); // TEST CODE
			e.printStackTrace();	
		} finally {
			closeRequest(searchBandName);
		}
		
		return band;
	}
	
	/**
	 * This methods inserts a new band in database.
	 * 
	 * @author Claire
	 * @param band
	 * @return result
	 * @version 20161116
	 * @throws FiftyCharException 
	 * @throws EmptyStringException 
	 * @throws SQLException 
	 */
	public int create(Band band) throws EmptyStringException, FiftyCharException, SQLException {
		
		result = 0;
		
		// TODO (Claire) method to split object
		id = band.getId();
		bandName = band.getName();
		bandBiography = band.getBiography();
		bandWebsite = band.getWebsite();
		
		isNameOK(bandName);
		
		try {
			createBand = connection.prepareStatement(SQLRequest.INSERT_INTO);
			
			createBand.setInt(1, id);
			createBand.setString(2, bandName);
			createBand.setString(3, bandBiography);
			createBand.setString(4, bandWebsite);
			
			result = createBand.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : le groupe n'a pas pu �tre cr��."); // TEST CODE
			e.printStackTrace();
			throw new SQLException("Le groupe n'a pas p� �tre enregistr�. Contactez l'administrateur.");
		} finally {
			closeRequest(createBand);
		}

		return result;
	}
	
	/**
	 * This method reads all band_id from database.
	 * 
	 * @author Claire
	 * @return idList
	 * @version 20161116
	 */
	private ArrayList<Integer> readAllId() {
		
		idList = new ArrayList<Integer>();
		id = 0;
		
		try {
			readAllId = connection.prepareStatement(SQLRequest.SELECT_ID);
			resultSet = readAllId.executeQuery();
			
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				idList.add(id);
			}
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : la liste d'ID n'a pas pu �tre affich�e."); // TEST CODE
			e.printStackTrace();
		} finally {
			closeRequest(readAllId);
		}
		
		return idList;
		
	}

	/**
	 * This method reads all band_name from database.
	 * 
	 * @author Claire
	 * @return nameList
	 * @version 20161117
	 */
	public ArrayList<String> readAllName() {
		
		bandNameList = new ArrayList<String>();
		
		try {
			readAllBandName = connection.prepareStatement(SQLRequest.SELECT_NAME_ORDERBY_BANDNAME);
			resultSet = readAllBandName.executeQuery();
			
			while (resultSet.next()) {
				bandName = resultSet.getString(1);
				bandNameList.add(bandName);
			}
			
			System.out.println("DAO:" + bandNameList);
			
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : la liste de nom(s) de groupe n'a pas pu �tre affich�e."); // TEST CODE
			e.printStackTrace();
		} finally {
			closeRequest(readAllBandName);
		}
		
		return bandNameList;
	}
	
	/**
	 * This method update a band in database.
	 * 
	 * @author Claire
	 * @param band
	 * @return result
	 * @version 20161120
	 */
	// TODO (Claire) update by band_id instead of band_name?
	public int update(Band band) {
		
		result = 0;
		
		bandName = band.getName();
		bandBiography = band.getBiography();
		bandWebsite = band.getWebsite();
		
		try {
			updateBand = connection.prepareStatement(SQLRequest.UPDATE_BAND);
			
			updateBand.setString(1, bandBiography);
			updateBand.setString(2, bandWebsite);
			updateBand.setString(3, bandName);
			
			result = updateBand.executeUpdate();
			
			System.out.println("DAO result update : " + result);
			
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : le groupe n'a pas pu �tre mis � jour."); // TEST CODE
			e.printStackTrace();
		} finally {
			closeRequest(updateBand);
		}
		
		return result; 
	}
	
	/**
	 * This method deletes a band in database.
	 * 
	 * @author Claire
	 * @param name
	 * @return result
	 * @version 20161120
	 */
	public int delete(String name) {
		
		result = 0;
		
		try {
			deleteBand = connection.prepareStatement(SQLRequest.DELETE_BAND);
			
			deleteBand.setString(1, name);
			result = deleteBand.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : le groupe n'a pas pu �tre supprim�."); // TEST CODE
			e.printStackTrace();
		} finally {
			closeRequest(deleteBand);
		}
		
		
		return result;
	}
	
	/**
	 * This method calls the readAllId method to return the bigger one.
	 * 
	 * @author Claire
	 * @return biggerId
	 */
	public int getBiggerId() {
		
		biggerId = 0;
		
		for (int id : this.readAllId()) {
			if (id > biggerId) {
				biggerId = id;
			}
		}
		
		return biggerId;
	}
	
	/**
	 * This method searches the name in parameter in database and return true if it exists.
	 * 
	 * @author Claire
	 * @param name
	 * @return exists
	 * @version 20161122
	 */
	public boolean check(String name) {
		
		boolean exists = false;
		
		try {
			checkBandName = connection.prepareStatement(SQLRequest.SELECT_BANDNAME_WHERE_BANDNAME);
			checkBandName.setString(1, name);
			
			resultSet = checkBandName.executeQuery();
			
			while (resultSet.next()) {
				bandName = resultSet.getString(1);
			}
			System.out.println("DAO, retour nom demand� : " + bandName);
			
		} catch (SQLException e) {
			System.err.println("Erreur DAO - requ�te incorrecte : la v�rification du nom n'a pas p� �tre effectu�e."); // TEST CODE
			e.printStackTrace();
		}
		
		if (bandName == null) {
			exists = true;
		}
		else {
			exists = false;
		}
		
		return exists;
		
	}
	
	/**
	 * Closes request and automatically result of request if there's one.
	 * 
	 * @author Claire
	 * @param prepStmt
	 * @version 20161116
	 */
	private void closeRequest(PreparedStatement prepStmt) {
		
		try {
			connection.commit();
		} catch (SQLException e1) {
			System.err.println("Erreur DAO : le commit ne s'est pas ex�cut�."); // TEST CODE
			e1.printStackTrace();
		}
		
		if (prepStmt != null) {
			try {
				prepStmt.close();
			} catch (SQLException e) {
				System.err.println("Erreur DAO : la requ�te n'a pas p� �tre cl�tur�e."); // TEST CODE
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method checks the band name received before trying an insert request.
	 * 
	 * @author Claire
	 * @param bandName
	 * @returns nameOK
	 * @version 20161123
	 * @throws EmptyStringException 
	 * @throws FiftyCharException 
	 */
	private boolean isNameOK(String bandName) throws EmptyStringException, FiftyCharException {
		
		boolean nameOK = false;
		
		if (ControlMethod.isEmptyOrNull(bandName)) {
			System.out.println("BandDAO, isNameOK : empty."); // TEST CODE
			throw new EmptyStringException("Le nom du groupe doit �tre renseign�.");
		}
		else if (ControlMethod.isSup50(bandName)) {
			System.out.println("BandDAO, isNameOK : too long."); // TEST CODE
			throw new FiftyCharException("Le nom du groupe ne peut pas d�passer cinquante caract�res.");
		}
		else {
			nameOK = true;
			return nameOK;
		}
		
	}
	
}
