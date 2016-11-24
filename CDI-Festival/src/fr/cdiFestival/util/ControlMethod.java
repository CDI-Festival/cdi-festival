package fr.cdiFestival.util;

/**
 * This class is for various method of test and control on attribute.
 * 
 * @author Claire
 * @version 20161123
 */
public class ControlMethod {
	
	private static boolean paramOK;
	private static String value;

	/**
	 * This method check if a String is empty or null
	 * 
	 * @author Claire
	 * @param param
	 * @return paramOK
	 * @version 20161123
	 */
	public static boolean isEmptyOrNull(String param) {
			
		paramOK = false;
		value = param.trim();
		
		if (value == null || value.isEmpty()) {
			paramOK = true;
		}
		else {
			paramOK = false;
		}
		
		return paramOK;
	}
		
	/**
	 * This method check if a String length is more than 50 characters.
	 * 
	 * @author Claire
	 * @param param
	 * @return paramOK
	 * @version 20161123
	 */
	public static boolean isSup50(String param){
		
		paramOK = false;
		value = param.trim();
		
		if (value.length() >= 50) {
			paramOK = true;
		}
		else {
			paramOK = false;
		}
		
		return paramOK;
	}
	
}
