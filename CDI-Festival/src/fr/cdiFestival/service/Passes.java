package fr.cdiFestival.service;

import java.util.ArrayList;

import fr.cdiFestival.model.Pass;


/**
 * Cette class crée un objet de type ArrayList specialisé pour stocker des objet de type Pass.
 * 
 * @author nicolas Tarral
 * @version : 2016-11-23
 *
 */
public class Passes extends ArrayList<Pass> {

	/**
	 * Cette methode retourne un Pass particulier grace à son numero de type.
	 * @param type entier representant un type de pass.
	 * @return un objet Pass.
	 */
	public Pass getAPass(int type) {

		Pass pass = new Pass();
		if (type >= 0 && type <= 6) {
			for (Pass current : this) {
				if (current.gettype() == type) {
					pass = current;

				}
			}
		}
		return pass;
	}

}
