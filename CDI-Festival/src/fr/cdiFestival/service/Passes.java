package fr.cdiFestival.service;

import java.util.ArrayList;

import fr.cdiFestival.model.Pass;



public class Passes extends ArrayList<Pass> {

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
