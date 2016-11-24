package fr.cdiFestival.servlet.band;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main controller for all the pages about bands.
 * 
 * @author Claire
 * @version 20161122
 */
@WebServlet(name = "BandController",
description = "Main controller for pages about bands.",
urlPatterns = {"/groupes/*", "/admin/groupes/*"}
		)
public class BandController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String pathInfo;
	private String servletPath;
	private RequestDispatcher dispatch;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BandController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		pathInfo = request.getPathInfo();
		servletPath = request.getServletPath();
		
		System.out.println("BandController, pathInfo: " + pathInfo); // TEST CODE
		System.out.println("BandController, servletPath: " + servletPath); // TEST CODE

		switch (pathInfo) {

		case "/":
			response.sendRedirect(request.getContextPath() + "/index-bandadmin.jsp");
			break;
		
		// Dispatch to the management servlet
		case "/gerer":
			
			System.out.println("BandController: groupes (menu) > dispatch to BandManagement"); // TEST CODE
			
			dispatch = request.getRequestDispatcher("/action/gerer/listergrp");
			dispatch.forward(request, response);
			break;

		// Redirect to the formulaire.jsp	
		case "/creer":
			
			System.out.println("BandController, create: liste-groupes.jsp > dispatch to formulaire.jsp"); // TEST CODE
			
			// response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/admin/formulaire.jsp");
			
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/admin/formulaire.jsp");
			dispatch.forward(request, response);
			break;
		
		// Dispatch to the management servlet
		case "/modifier":
			
			System.out.println("BandController, update: liste-groupe.jsp > dispatch to BandManagement"); // TEST CODE
			
			dispatch = request.getRequestDispatcher("/action/gerer/modifiergrp");
			dispatch.forward(request, response);
			break;
			
		// Dispatch to the management servlet
		case "/supprimer":
			
			System.out.println("BandController, delete: liste-groupes.jsp > dispatch to BandManagement"); // TEST CODE
			
			dispatch = request.getRequestDispatcher("/action/gerer/supprimergrp");
			dispatch.forward(request, response);
			break;

		default:
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur404.jsp");
			dispatch.forward(request, response);
			System.out.println("BandController : page 404."); // TEST CODE
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		pathInfo = request.getPathInfo();
		servletPath = request.getServletPath();
		System.out.println("BandController, pathInfo: " + pathInfo); // TEST CODE
		System.out.println("BandController, servletPath: " + servletPath); // TEST CODE

		if (pathInfo.equals("/enregistrer")) {
			
			System.out.println("BandController: formulaire.jsp > dispatch to BandManagement"); // TEST CODE
			
			dispatch = request.getRequestDispatcher("/action/gerer/enregistrergrp");
			dispatch.forward(request, response);
		}
		
		else {
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/error/erreur404.jsp");
			dispatch.forward(request, response);
			System.out.println("BandController : page 404."); // TEST CODE
		}
		
	}
}
