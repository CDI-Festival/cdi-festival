package fr.cdiFestival.dao.band;

/**
 * SQL request for BandDAO.
 * 
 * @author Claire
 * @version 20161119
 */
public class SQLRequest {

	// Insert request
	/**
	 * INSERT INTO cdifestival_band VALUES (?, ?, ?, ?)
	 */
	public final static String INSERT_INTO = "INSERT INTO cdifestival_band VALUES (?, ?, ?, ?)";
	
	
	// Select requests
	/**
	 * SELECT band_name FROM cdifestival_band ORDER BY band_name
	 */
	public final static String SELECT_NAME_ORDERBY_BANDNAME = "SELECT band_name FROM cdifestival_band ORDER BY band_name";
	
	/**
	 * SELECT band_name, band_biography, band_website FROM cdifestival_band WHERE band_name = ?
	 */
	public final static String SELECT_WHERE_BANDNAME = "SELECT band_name, band_biography, band_website"
			+ " FROM cdifestival_band WHERE band_name = ?";
	
	/**
	 * SELECT band_name FROM cdifestival_band WHERE band_name = ?
	 */
	public final static String SELECT_BANDNAME_WHERE_BANDNAME = "SELECT band_name FROM cdifestival_band WHERE band_name = ?";
	
	/**
	 * SELECT band_id FROM cdifestival_band
	 */
	public final static String SELECT_ID = "SELECT band_id FROM cdifestival_band";
	
	
	// Update request
	/**
	 * UPDATE cdifestival_band SET band_biography = ?, band_website = ? WHERE band_name = ?
	 */
	public final static String UPDATE_BAND = "UPDATE cdifestival_band"
			+ " SET band_biography = ?, band_website = ?"
			+ " WHERE band_name = ?";
	
	
	// Delete request
	/**
	 * DELETE FROM cdifestival_band WHERE band_name = ?
	 */
	public final static String DELETE_BAND = "DELETE FROM cdifestival_band WHERE band_name = ?";
	
}
