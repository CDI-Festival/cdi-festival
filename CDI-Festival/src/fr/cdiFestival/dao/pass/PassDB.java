package fr.cdiFestival.dao.pass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import fr.cdiFestival.model.Pass;
import fr.cdiFestival.service.Passes;

/**
 * Class which is going to manipulate data with the database, it is going to
 * implement all CRUD methods.
 * 
 * @author nicolas Tarral
 * @version 2016-11-21
 *
 */
public class PassDB {

	private static final String TABLE_NAME = "pass";
	private static final String TYPE = "type";
	private static final String NUMBERS = "numbers";
	private static final String PRICE = "price";
	private static final String DAY_DESCRIPTION = "day_description";
	private static final String DAY = "day";

	/**
	 * Method used to insert a Pass in the BDD
	 * 
	 * @param passToInsert
	 *            is a Pass object.
	 * @throws DaoException
	 */
	public static void insertPass(Pass passToInsert) throws DaoException {

		if (passToInsert != null) {

			Connection connection = null;
			PreparedStatement statement = null;

			try {
				connection = DBConnection.getConnect();

				String date1 = null;
				String date2 = null;
				String date3 = null;
				String date = null;

				int type = passToInsert.gettype();
				int number = passToInsert.getNombre();
				int price = passToInsert.getPrice();
				String dayType = passToInsert.getDayType();

				// transforming LocalDate Array in a String separated by ",".

				String comma = "";
				StringBuilder allGenres = new StringBuilder();
				for (LocalDate g : passToInsert.getDate()) {
					allGenres.append(comma);
					allGenres.append(localDateToString(g));
					comma = ",";
				}

				date = allGenres.toString();

				String insertQuery = "insert into pass (" + TYPE + "," + NUMBERS + "," + PRICE + "," + DAY_DESCRIPTION
						+ ", " + DAY + ") values (?, ?, ?, ?, ?)";

				statement = connection.prepareStatement(insertQuery);
				statement.setInt(1, type);
				statement.setInt(2, number);
				statement.setInt(3, price);
				statement.setString(4, dayType);
				statement.setString(5, date);

				statement.executeUpdate();
				connection.commit();

			} catch (SQLException e) {
				System.out.println("erreur SQL");
				throw new DaoException("SQL error in (Insert).");

			}
		} else {
			throw new DaoException("Object to insert is null.");
		}

	}

	/**
	 * This method is going to return all the passes stored in Database.
	 * 
	 * @return an ArrayList of Passes
	 * @throws SQLException
	 * @throws DaoException
	 */
	public static Passes getAllPAss() throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Pass monPass = null;
		Passes myPasses = new Passes();
		ArrayList<LocalDate> daysLocal = null;
		ArrayList<String> days = null;
		String[] allDays = null;

		connection = DBConnection.getConnect();

		if (connection == null) {
			throw new DaoException("Connection inexistante.");
		}

		String query = "SELECT type, numbers, price, day_description, day from PASS";

		int typePass = 0;
		int numbers = 0;
		int price = 0;
		String dayDesc = null;
		String date = null;

		try {
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			connection.commit();

			while (rs.next()) {

				monPass = new Pass();
				typePass = rs.getInt("TYPE");
				numbers = rs.getInt("NUMBERS");
				price = rs.getInt("PRICE");
				dayDesc = rs.getString("DAY_DESCRIPTION");
				date = rs.getString("DAY");

				allDays = date.split(",");
				daysLocal = new ArrayList<LocalDate>();
				days = new ArrayList<String>(Arrays.asList(allDays));
				for (String current : days) {
					daysLocal.add(StringToLocalDate(current));
				}

				monPass.setType(typePass);
				monPass.setNombre(numbers);
				monPass.setPrice(price);
				monPass.setDayType(dayDesc);
				monPass.setDates(daysLocal);
				myPasses.add(monPass);
			}

		} catch (SQLException e) {
			System.out.println("erreur SQL");
			throw new DaoException("SQL error in (GET).");
		}
		return myPasses;

	}

	/**
	 * this method will return a pass by its Type Id
	 * 
	 * @param the
	 *            type (int) of the Pass to look for.
	 * @return a Pass object.
	 * @throws DaoException
	 */
	public static Pass getPass(int type) throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Pass monPass = null;
		ArrayList<LocalDate> daysLocal = new ArrayList<LocalDate>();

		try {
			String query = "SELECT type, numbers, price, day_description, day from pass WHERE type = ?";
			connection = DBConnection.getConnect();
			statement = connection.prepareStatement(query);
			statement.setInt(1, type);
			rs = statement.executeQuery();
			connection.commit();

			while (rs.next()) {

				monPass = new Pass();
				int typePass = rs.getInt("TYPE");
				int numbers = rs.getInt("NUMBERS");
				int price = rs.getInt("PRICE");
				String dayDesc = rs.getString("DAY_DESCRIPTION");
				String date = rs.getString("DAY");
				String[] allDays = date.split(",");

				System.out.println(typePass);

				ArrayList<String> days = new ArrayList<String>(Arrays.asList(allDays));
				for (String current : days) {
					daysLocal.add(StringToLocalDate(current));
				}

				monPass.setType(typePass);
				monPass.setNombre(numbers);
				monPass.setPrice(price);
				monPass.setDayType(dayDesc);
				monPass.setDates(daysLocal);

			}

		} catch (SQLException e) {
			System.out.println("erreur SQL ");
			throw new DaoException("SQL error in (getPass).");
		}
		return monPass;

	}

	/**
	 * This method is going to update pass quantity
	 * 
	 * @param passtoEdit
	 * @throws DaoException
	 */
	public static void updatePassQuantity(Pass passtoEdit) throws DaoException {

		if (passtoEdit != null) {

			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			int row = 0;

			try {
				connection = DBConnection.getConnect();
				String updateSQL = "UPDATE pass SET numbers = ? WHERE TYPE = ?";

				statement = connection.prepareStatement(updateSQL);

				statement.setInt(1, passtoEdit.getNombre());
				statement.setInt(2, passtoEdit.gettype());
				row = statement.executeUpdate();
				connection.commit();

			} catch (SQLException e) {
				System.out.println("erreur SQL ");
				throw new DaoException("SQL error in (update Quantity).");
			}
		} else {
			throw new DaoException("Object to insert is null.");
		}

	}

	/**
	 * This method is going to change price of a pass.
	 * 
	 * @param passtoEdit
	 * @throws DaoException
	 */
	public static void updatePass(Pass passtoEdit) throws DaoException {

		if (passtoEdit != null) {

			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			int row = 0;

			try {
				connection = DBConnection.getConnect();
				String updateSQL = "UPDATE pass SET PRICE = ? WHERE TYPE = ?";

				statement = connection.prepareStatement(updateSQL);

				statement.setInt(1, passtoEdit.getPrice());
				statement.setInt(2, passtoEdit.gettype());

				row = statement.executeUpdate();
				connection.commit();

			} catch (SQLException e) {
				System.out.println("erreur SQL ");
				throw new DaoException("SQL error in (update Price).");
			}

		} else {
			throw new DaoException("Object to insert is null.");
		}

	}

	/**
	 * Delete all passes from database.
	 * 
	 * @throws DaoException
	 */
	public static void deleteAllPAss() throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int row = 0;

		try {
			connection = DBConnection.getConnect();

			String query = "DELETE FROM pass";

			statement = connection.prepareStatement(query);

			row = statement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			System.out.println("erreur SQL ");
			throw new DaoException("SQL error in (update Quantity).");

		}

	}

	/**
	 * This static method is going to be used to convert LocalDateTime in a
	 * String format this way dd MM yyyy HH:MM:SS
	 * 
	 * @param input
	 *            the date to convert in LocalDateTime
	 * @return the output date in String format.
	 */
	public static String localDateToString(LocalDate input) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uuuu");
		String text = input.format(formatter);

		return text;

	}

	/**
	 * Cette methode statique va convertir une date de type String de format
	 * "dd MM uuuu HH:mm:ss" en type LocalDateTime
	 * 
	 * @param input
	 *            represente la date en String
	 * @return un objet de type LocalDateTime.
	 */
	public static LocalDate StringToLocalDate(String input) {
		LocalDate localTime = null;
		if (input != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uuuu");
			localTime = LocalDate.parse(input, formatter);

		}
		return localTime;
	}

}
