package fr.cdiFestival.servlet.band;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.BandDAO;
import fr.cdiFestival.model.Band;

/**
 * Servlet to manage DAO calls for band pages.
 * 
 * @author Claire
 * @version 20161120
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
	
	private String validationType;

	// Attributes for DB
	private BandDAO bandDAO;
	private int result;
		
	// Attributes to handle return result from DB
	private ArrayList<String> bandNameList;

	// Attributes to handle band object
	private Band band;
	private String name;
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
			
			name = request.getParameter("value");
			
			// Asks BandDAO the DB informations of the band sent in parameter
			bandDAO = new BandDAO();
			band = bandDAO.search(name);
			
			System.out.println(band); // TEST CODE
			
			// Dispatch band to formulaire.jsp
			System.out.println("BandManagement: liste-groupes.jsp > dispatch to formulaire.jsp"); // TEST CODE
			
			request.setAttribute("band", band);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/formulaire.jsp");
			dispatch.forward(request, response);
			break;
			
		case "/supprimergrp":
			
			name = request.getParameter("value");
			
			// Asks BandDAO to delete the band which name is sent in parameter
			bandDAO = new BandDAO();
			result = bandDAO.delete(name);
			
			System.out.println(result); // TEST CODE
			
			if (result != 0) {
				
				System.out.println("BandManagement, delete done: > BandController"); // TEST CODE
				
				dispatch = request.getRequestDispatcher("/admin/groupes/gerer");
				dispatch.forward(request, response);
			}
			break;
			
		default:
			// TODO (Claire) error page
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
				// TODO (Claire) default switch enregistrer
				break;
			}
			
			// TODO (Claire) controls data + error display or use JS?

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
	 * @version 20161120
	 */
	private void createBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO (Claire) check Exception
		
		// If band doesn't exist already
		if (checkDB(request)) {
		
			// Creates a new band object
			band = createObjectBand(request);
			
			// Asks BandDAO to insert the new band in DB
			bandDAO = new BandDAO();
			result = bandDAO.create(band);
			
			System.out.println("BandManagement: create, formulaire.jsp > dispatch to validation.jsp"); // TEST CODE
	
			// Set the validation type in validation.jsp
			validationType = "créé";
			
			if (result != 0) {
				request.setAttribute("name", name);
				request.setAttribute("validationType", validationType);
				dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/validation.jsp");
				dispatch.forward(request, response);
			}
			else {
				// TODO (Claire) error page
			}
		
		}
		
		else {
			// TODO send pop-up error, by JS?
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
		
		// Creates a new band object.
		band = createObjectBand(request);
		
		// Asks BandDAO to update the band in DB.
		bandDAO = new BandDAO();
		result = bandDAO.update(band);
		
		System.out.println("BandManagement: update, formulaire.jsp > dispatch to validation.jsp"); // TEST CODE
		
		// To let validation.jsp if it's a creation or an update
		validationType = "mis à jour";
		
		if (result != 0) {
			request.setAttribute("name", name);
			request.setAttribute("validationType", validationType);
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/validation.jsp");
			dispatch.forward(request, response);
		}
		else {
			// TODO (Claire) error page
		}
		
	}
	
	/** This method check if the name choose to create a new band already exist in DB.
	 * 
	 * @author Claire
	 * @param request
	 * @return 
	 * @version 20161122
	 */
	private boolean checkDB(HttpServletRequest request) {
		
		// Get the name from form input
		name = request.getParameter("bandname");
		
		
		
		return true;
	}
	
	/**
	 * This method create a new band object.
	 * 
	 * @author Claire
	 * @param request
	 * @return band
	 * @version 20161120
	 */
	private Band createObjectBand(HttpServletRequest request) {

		// Get the form inputs
		name = request.getParameter("bandname");
		biography = request.getParameter("bandbio");
		website = request.getParameter("bandwebsite");
		
		Band band;
		band = new Band(name, biography, website);
		return band;
	}

}
