package fr.cdiFestival.servlet.pass;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.pass.DaoException;
import fr.cdiFestival.service.Passes;
import fr.cdiFestival.technic.StockPass;

/**
 * This Servlet is going to take care of the input and output to displqy,
 * create, modify and delete all the pass.
 * 
 * @author Nicolas Tarral
 * @version 2016-11-22
 */
@WebServlet(name = "ManagePass", description = "Controleur Manager", urlPatterns = { "/ControlManager/*" })
public class ManagePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_SPOT = 1000;
	
	
	private String url;
	private int[] dayQuantity = new int[6];
	private int[] prix = new int[3];
	private StockPass stock;
	
	/**
	 * Default constructor.
	 */
	public ManagePass() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		

		url = "/WEB-INF/pass/gestion.jsp";
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		stock = (StockPass) getServletContext().getAttribute("stock");
		//StockPass stock = new StockPass();
		Passes lesStock = null;
		try {
			lesStock = stock.getAllDBPass();
			int nombre = 5;
			request.setAttribute("day", nombre);

			request.setAttribute("day1", lesStock.getAPass(0).getNombre());
			request.setAttribute("day2", lesStock.getAPass(1).getNombre());
			request.setAttribute("day3", lesStock.getAPass(2).getNombre());
			request.setAttribute("day12", lesStock.getAPass(3).getNombre());
			request.setAttribute("day23", lesStock.getAPass(5).getNombre());
			request.setAttribute("day123", lesStock.getAPass(6).getNombre());

			request.setAttribute("price1", lesStock.getAPass(0).getPrice());
			request.setAttribute("price23", lesStock.getAPass(3).getPrice());
			request.setAttribute("price123", lesStock.getAPass(6).getPrice());

			request.setAttribute("allRow", lesStock);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

		} catch (DaoException e) {
			System.out.println("erreur SQL dans Manage Pass");
			request.setAttribute("error", e.getMessage());
			url = "/gestionErreur/sql";

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String button = request.getParameter("button");
		stock = (StockPass) getServletContext().getAttribute("stock");
		
		try {
			stock = initParameters(request, response);
		} catch (DaoException e2) {
			request.setAttribute("error", e2.getMessage());
			url = "/gestionErreur/sql";
		}
		if (stock.getAllPass() == null && !button.equals("Supprimer")) {
			try {
				dispatchToGestion(request, response, stock, "/gestionErreur/Error");
			} catch (DaoException e) {
				request.setAttribute("error", e.getMessage());
				url = "/gestionErreur/sql";
			}
		} else {

			// Ajout à la base.

			if (button.equals("Ajouter")) {
				try {
					stock.emptyTable();
					stock.storeInDatabase();
					dispatchToGestion(request, response, stock, url);
				} catch (DaoException e) {
					request.setAttribute("error", e.getMessage());
					url = "/gestionErreur/sql";
				}

				// Modification de la base.

			} else if (button.equals("Modifier")) {
				try {
					stock.updatePrice();
					dispatchToGestion(request, response, stock, url);
				} catch (DaoException e) {
					request.setAttribute("error", e.getMessage());
					url = "/gestionErreur/sql";
				}

			} else if (button.equals("Supprimer")) {
				try {

					stock.emptyTable();
					dispatchToGestion(request, response, stock, url);
				} catch (DaoException e) {
					request.setAttribute("error", e.getMessage());
					url = "/gestionErreur/sql";
				}
			} else {
				try {
					url = "gestionErreur/error";
					dispatchToGestion(request, response, stock, url);
				} catch (DaoException e) {
					request.setAttribute("error", e.getMessage());
					url = "/gestionErreur/sql";
				}
			}
		}
	}

	/*
	 * This method is going to validate the parameters coming from the form. it
	 * will provide a list
	 */
	private StockPass initParameters(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, DaoException {
		stock = (StockPass) getServletContext().getAttribute("stock");
		String[] formValues = request.getParameterValues("form");
		String[] priceValues= request.getParameterValues("price");


		if (isFormCorrect(formValues, priceValues)) {
			stock = new StockPass(prix[0], prix[1], prix[2], dayQuantity[0], dayQuantity[1], dayQuantity[2],
					dayQuantity[3], dayQuantity[4], dayQuantity[5]);
			stock.generateTickets(false);
			url = "/WEB-INF/pass/gestion.jsp";
		} else {
			url = "/WEB-INF/gestion.jsp";
		}
		return stock;
	}

	/**
	 * @param formValues
	 * @param priceValues
	 */
	private boolean isFormCorrect(String[] formValues, String[] priceValues) {
		if (formValues == null || priceValues == null) {
			return false;
		} else {

			// Calcul des places chaque jours doit etre <= 1000
			int prix1 = 0;
			int prix2 = 0;
			int prix3 = 0;

			int zero = 0;
			int one = 0;
			int two = 0;
			int three = 0;
			int four = 0;
			int five = 0;
			try {

				prix1 = Integer.parseInt(priceValues[0]);
				prix2 = Integer.parseInt(priceValues[1]);
				prix3 = Integer.parseInt(priceValues[2]);
				System.out.println(prix1 + " " + prix2 + " " + prix3);

				zero = Integer.parseInt(formValues[0]);
				one = Integer.parseInt(formValues[1]);
				two = Integer.parseInt(formValues[2]);
				three = Integer.parseInt(formValues[3]);
				four = Integer.parseInt(formValues[4]);
				five = Integer.parseInt(formValues[5]);
				System.out.println(formValues[3] + " " + formValues[4] + " " + formValues[5]);
			} catch (NumberFormatException n) {
				System.out.println("one of the paraméter is not a number or field is empty !");

				return false;

			}


			if (prix1 == 0 && prix2 == 0 && prix3 == 0 && zero == 0 && one == 0 && two == 0 && three == 0 && four == 0
					&& five == 0) {

				// TODO (nicolas) line to be removed for now
				// stock.generateTickets(true);
			//	System.out.println("---MANAGEPASS--- tous les paramètres sont a 0; lancement du generateur ---");
				// url = "/WEB-INF/gestion.jsp";
				return false;

			} else {

				dayQuantity[0] = zero;
				dayQuantity[1] = one;
				dayQuantity[2] = two;
				dayQuantity[3] = three;
				dayQuantity[4] = four;
				dayQuantity[5] = five;

				for (int i = 0; i <= 5; i++) {
					if (dayQuantity[i] > MAX_SPOT || dayQuantity[i] <= 0) {
						System.out.println(
								"---MANAGEPASS--- journée dépasse le nombre max de place (1000) ou infèrieur a 0 à position : "
										+ i);
						return false;
					}
				}

				int fridaySpot = zero + three + five;
				int saturdaySpot = one + three + four + five;
				int sundaySpot = two + four + five;

				if (fridaySpot > MAX_SPOT) {
					System.out.println("---MANAGEPASS--- nombre place trop grande pour vendredi");
					return false;
				}
				if (saturdaySpot > MAX_SPOT) {
					System.out.println("---MANAGEPASS--- nombre place  trop grande pour samedi");
					return false;
				}
				if (sundaySpot > MAX_SPOT) {
					System.out.println("---MANAGEPASS--- nombre place  trop grande pour dimanche");
					return false;
				} else {
					System.out.println("---MANAGEPASS--- [cumul des places] tout est parfait Bravo !");
				}

				// Price's check

				if (prix1 <= 0 || prix2 <= 0 || prix3 <= 0) {
					System.out.println("---MANAGEPASS--- un des prix est infèrieur a 0");
					return false;
				}

				if ((prix1 >= prix2) || (prix1 >= prix3)) {
					System.out.println(
							"---MANAGEPASS--- Prix d'une place ne peut-être supèrieur au prix de 2 ou 3 jours.");
					System.out.println(prix1 + " >= " + prix2 + " || " + prix1 + " >= " + prix2);
					return false;

				} else {
					System.out.println(
							"---MANAGEPASS--- [prix 0 est infèrieur a prix 1 et prix 2] tout est parfait Bravo !");
				}

				if ((prix2 >= prix3)) {
					System.out.println(
							"---MANAGEPASS--- Prix d'une place 2 jours ne peut-être supèrieur au prix d'une place de 3 jours.");
					return false;
				} else {
					System.out.println("---MANAGEPASS--- [prix 2 est infèrieur a prix 3] tout est parfait Bravo !");
				}
			}
			prix[0] = prix1;
			prix[1] = prix2;
			prix[2] = prix3;
			return true;
		}
	}

	/*
	 * This method is going to take care of the date that need to be displayed
	 * on the JSP page. it will get all datas from DB - load all the necessary
	 * attribute for the jsp. - and forward the request to the JSP page.
	 */
	private void dispatchToGestion(HttpServletRequest req, HttpServletResponse res, StockPass stock, String uri)
			throws IOException, ServletException, DaoException {

		if (uri.equals("/gestionErreur/Error")) {
			RequestDispatcher rd = req.getRequestDispatcher("/gestionErreur/Err");
			rd.forward(req, res);
		} else {
			Passes lesStock = null;
			try {
				lesStock = stock.getAllDBPass();
			} catch (DaoException e) {
				System.out.println("erreur SQL dans Manage Pass");
				req.setAttribute("error", e.getMessage());
				url = "/gestionErreur/sql";
			}

			System.out.printf("lesStock" + lesStock.toString());

			System.out.println("prepare to display on html...");
			req.setAttribute("allRow", lesStock);

			req.setAttribute("day1", lesStock.getAPass(0).getNombre());
			req.setAttribute("day2", lesStock.getAPass(1).getNombre());
			req.setAttribute("day3", lesStock.getAPass(2).getNombre());
			req.setAttribute("day12", lesStock.getAPass(3).getNombre());
			req.setAttribute("day23", lesStock.getAPass(5).getNombre());
			req.setAttribute("day123", lesStock.getAPass(6).getNombre());

			req.setAttribute("price1", lesStock.getAPass(0).getPrice());
			req.setAttribute("price23", lesStock.getAPass(3).getPrice());
			req.setAttribute("price123", lesStock.getAPass(6).getPrice());

			res.setContentType("text/html");
			res.setHeader("Cache-Control", "no-cache");

			RequestDispatcher rd = req.getRequestDispatcher(uri);
			rd.forward(req, res);
		}
	}

}
