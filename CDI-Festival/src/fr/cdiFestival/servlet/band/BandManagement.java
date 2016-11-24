package fr.cdiFestival.servlet.band;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dal.band.BandDAO;
import fr.cdiFestival.exceptions.EmptyStringException;
import fr.cdiFestival.exceptions.FiftyCharException;
import fr.cdiFestival.model.Band;
import fr.cdiFestival.util.ControlMethod;

/**
 * Servlet to manage DAO calls for band pages.
 * 
 * @author Claire
 * @version 20161123
 */
@WebServlet(name = "BandManagement",
description = "Servlet to manage DAO calls for band.",
urlPatterns = {"/action/gerer/*"}
		)
public class BandManagement extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Attributes for controller
	private String pathInfo;
	private String servletPath;
	private RequestDispatcher dispatch;

	private String error;
	private String validationType;

	// Attributes for DB
	private BandDAO bandDAO;
	private int result;
		
	// Attributes to handle return result from DB
	private ArrayList<String> bandNameList;

	// Attributes to handle band object
	private Band band;
	private String bandName;
	private String biography;
	private String website;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BandManagement() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		pathInfo = request.getPathInfo();
		servletPath = request.getServletPath();
		
		System.out.println("BandManagement, pathInfo: " + pathInfo); // TEST CODE
		System.out.println("BandController, servletPath: " + servletPath); // TEST CODE

		switch (pathInfo) {
		
		case "/listergrp":
			// Asks BandDAO the name(s) list of the registered bands in DB
			bandDAO = new BandDAO();
			bandNameList = bandDAO.readAllName();
			
			System.out.println("BandManagement: " + bandNameList); // TEST CODE

			// Dispatch bandNameList to liste-groupes.jsp
			System.out.println("BandManagement: groupes > dispatch to liste-groupes.jsp"); // TEST CODE
			
			request.setAttribute("bandNameList", bandNameList);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/liste-groupes.jsp");
			dispatch.forward(request, response);
			break;

		case "/modifiergrp":
			
			bandName = request.getParameter("value");
			
			// Asks BandDAO the DB informations of the band sent in parameter
			bandDAO = new BandDAO();
			band = bandDAO.search(bandName);
			
			System.out.println(band); // TEST CODE
			
			// Dispatch band to formulaire.jsp
			System.out.println("BandManagement: liste-groupes.jsp > dispatch to formulaire.jsp"); // TEST CODE
			
			request.setAttribute("band", band);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/formulaire.jsp");
			dispatch.forward(request, response);
			break;
			
		// TODO (Claire) a effectuer en doPost
		case "/supprimergrp":
			
			bandName = request.getParameter("value");
			
			// Asks BandDAO to delete the band which name is sent in parameter
			bandDAO = new BandDAO();
			result = bandDAO.delete(bandName);
			
			System.out.println(result); // TEST CODE
			
			if (result != 0) {
				
				System.out.println("BandManagement, delete done: > BandController"); // TEST CODE
				
				response.sendRedirect(request.getContextPath() + "/admin/groupes/gerer");
			}
			else {
				error = "la suppression";
				request.setAttribute("erreur", error);
				dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur.jsp");
				dispatch.forward(request, response);
			}
			break;
			
		default:
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur404.jsp");
			dispatch.forward(request, response);
			System.out.println("BandManagement: page 404.");
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		pathInfo = request.getPathInfo();
		servletPath = request.getServletPath();
		
		System.out.println("BandManagement, pathInfo: " + pathInfo); // TEST CODE
		System.out.println("BandController, servletPath: " + servletPath); // TEST CODE

		if (pathInfo.equals("/enregistrergrp")) {
			
			// Create or update a band
			switch (checkButton(request)) {
			case "Enregistrer":
				createBand(request, response);
				break;
			case "Enregistrer les modifications":
				updateBand(request, response);
				break;
			default:
				error = "l'enregistrement";
				request.setAttribute("erreur", error);
				dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur.jsp");
				dispatch.forward(request, response);
				break;
			}

		}
		else {
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur404.jsp");
			dispatch.forward(request, response);
			System.out.println("BandManagement : page 404."); // TEST CODE
		}

	}
	
	/**
	 * This method checks if it's a creation or an update by getting the form button value.	
	 * 
	 * @author Claire
	 * @param request
	 * @return button
	 * @version 20161120
	 */
	private String checkButton(HttpServletRequest request) {
		
		String button;
		button = request.getParameter("savebutton");
		
		System.out.println("BandManagement: " + button); // TEST CODE
		
		return button;
	}
	
	/**
	 * This method create a new object and calls DAO class to do an insert in database.
	 * 
	 * @author Claire
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @version 20161123
	 */
	private void createBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name;
		// TODO (Claire) change boolean with exception in method checkDB
		boolean checkOK;
		
		// Get the name from form input
		name = request.getParameter("bandname");
		// Trims name
		bandName = name.trim();	
		System.out.println("BandManagement, trim nom checkDB : " + bandName); // TEST CODE
		
		try {
			// Checks if bandName isn't empty or too long, can throw exception
			isNameOK(bandName);
			// Checks if bandName doesn't already exist
			checkOK = checkDB(bandName);
		
				if (checkOK) {
				
					// Creates a new band object, can throw an exception
					band = createObjectBand(request);
				
					// Asks BandDAO to insert the new band in DB
					bandDAO = new BandDAO();
					result = bandDAO.create(band);
					
					System.out.println("BandManagement: create, formulaire.jsp > dispatch to validation.jsp"); // TEST CODE
					
					if (result != 0) {
						
						// Set the validation type in validation.jsp
						validationType = "a bien été créé";
						
						request.setAttribute("name", bandName);
						request.setAttribute("validationType", validationType);
						dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/validation.jsp");
						dispatch.forward(request, response);
					}
				
				}
				// If checkDB not OK
				else {
				
					// Set the validation type in validation.jsp
					validationType = "existe déjà";
					
					request.setAttribute("name", bandName);
					request.setAttribute("validationType", validationType);
					dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/validation.jsp");
					dispatch.forward(request, response);
				}
		
		// If exceptions
		} catch (EmptyStringException | FiftyCharException | SQLException e) {
			System.out.println("BandManagement, createBand : " + e.getMessage()); // TEST CODE
			
			error = "la création";
			String errorInfo = e.getMessage();
			request.setAttribute("erreur", error);
			request.setAttribute("erreurInfo", errorInfo);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur.jsp");
			dispatch.forward(request, response);
		}
}
	
	/**
	 * This method create a new band object and calls DAO class to do an update in database.
	 * 
	 * @author Claire
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @version 20161120
	 */
	private void updateBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO (Claire) handle exception
		
		// Creates a new band object
		try {
			band = createObjectBand(request);
		} catch (EmptyStringException | FiftyCharException e) {
			System.out.println("BandManagement, createObjectBand : " + e);
			e.printStackTrace();
		}
		
		// Asks BandDAO to update the band in DB.
		bandDAO = new BandDAO();
		result = bandDAO.update(band);
		
		System.out.println("BandManagement update : " + band.getName());
		System.out.println("BandManagement result update : " + result);
		
		System.out.println("BandManagement: update, formulaire.jsp > dispatch to validation.jsp"); // TEST CODE
		
		if (result != 0) {
			
			// To let validation.jsp if it's a creation or an update
			validationType = "a bien été mis à jour";
			
			request.setAttribute("name", bandName);
			request.setAttribute("validationType", validationType);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/validation.jsp");
			dispatch.forward(request, response);
		}
		else {
			error = "la modification";
			request.setAttribute("erreur", error);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur.jsp");
			dispatch.forward(request, response);
		}
		
	}
	
	/**
	 * This method checks the band name field.
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
			System.out.println("BandManagement, isNameOK : empty."); // TEST CODE
			throw new EmptyStringException("Le nom du groupe doit être renseigné.");
		}
		else if (ControlMethod.isSup50(bandName)) {
			System.out.println("BandManagement, isNameOK : too long."); // TEST CODE
			throw new FiftyCharException("Le nom du groupe ne peut pas dépasser cinquante caractères.");
		}
		else {
			nameOK = true;
			return nameOK;
		}
		
	}
	
	/** This method check if the name choose to create a new band already exists in DB.
	 * 
	 * @author Claire
	 * @param request
	 * @return exists
	 * @version 20161122
	 */
	private boolean checkDB(String bandName) {
		
		boolean exists;
		
		bandDAO = new BandDAO();
		exists = bandDAO.check(bandName);
		
		if (exists) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method create a new band object.
	 * 
	 * @author Claire
	 * @param request
	 * @return band
	 * @version 20161120
	 */
	private Band createObjectBand(HttpServletRequest request) throws EmptyStringException, FiftyCharException {

		Band band;
		band = null;
		
		// Get the form inputs
		bandName = request.getParameter("bandname");
		biography = request.getParameter("bandbio");
		website = request.getParameter("bandwebsite");
		
		band = new Band(bandName, biography, website);
		
		return band;
	}

}
