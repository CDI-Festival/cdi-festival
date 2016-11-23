package fr.cdiFestival.servlet.pass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.pass.DaoException;
import fr.cdiFestival.service.Passes;
import fr.cdiFestival.technic.StockPass;


/**
 * This Servlet is going to take care of the input and output to displqy, create, modify and delete all the pass.
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

		String button = (String) request.getAttribute("generate");
		System.out.println("appuie sur bouton" + button);

		StockPass stock = new StockPass();
		System.out.println("inthe GET method");
		Passes lesStock = null;
		try {
			lesStock = stock.getAllDBPass();
			System.out.println("prepare to display on html..." + lesStock);

			int nombre = 5;
			request.setAttribute("day", nombre);

			request.setAttribute("day1", lesStock.getAPass(0).getNombre());
			request.setAttribute("day2", lesStock.getAPass(1).getNombre());
			request.setAttribute("day3", lesStock.getAPass(2).getNombre());
			request.setAttribute("day12", lesStock.getAPass(3).getNombre());
			request.setAttribute("day23", lesStock.getAPass(5).getNombre());
			request.setAttribute("day123", lesStock.getAPass(6).getNombre());

			System.out.println(lesStock.getAPass(6).toString());

			request.setAttribute("price1", lesStock.getAPass(0).getPrice());
			request.setAttribute("price23", lesStock.getAPass(3).getPrice());
			request.setAttribute("price123", lesStock.getAPass(6).getPrice());

			request.setAttribute("allRow", lesStock);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");

		} catch (Exception e) {
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

		System.out.println("--- MANAGEPASS int doPost ---");

		String button = request.getParameter("button");
		System.out.println("appuie sur bouton" + button);

		StockPass stockPack = new StockPass();
		try {
			stockPack = initParameters(request, response);
		} catch (DaoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}if (stockPack.getAllPass() == null && !button.equals("Supprimer")){
			try {
				dispatchToGestion(request, response, stockPack, "/gestionErreur/Error");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
		

		// Ajout à la base.

		if (button.equals("Ajouter")) {
			System.out.println("vous avez cliquer sur " + button);
			stockPack.emptyTable();
			stockPack.storeInDatabase();
			try {
				// dispatchToGestion(request, response, stockPack,
				// "/WEB-INF/gestion.jsp");
				dispatchToGestion(request, response, stockPack, url);
			} catch (DaoException e) {
				// TODO (nicolas) check exception
				e.printStackTrace();
			}

			// Modification de la base.

		} else if (button.equals("Modifier")) {
			System.out.println("vous avez cliquer sur " + button);
			stockPack.updatePrice();

			try {
				dispatchToGestion(request, response, stockPack, url);
			} catch (DaoException e) {
				// TODO (nicolas) check exception
				e.printStackTrace();
			}

		} else if (button.equals("Supprimer")) {
			System.out.println("vous avez cliquer sur " + button);
			stockPack.emptyTable();
			try {
				dispatchToGestion(request, response, stockPack, url);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("mauvais choix!");
			url = "gestionErreur/error";
			try {
				dispatchToGestion(request, response, stockPack, url);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

//		try {
//			stockPack.niceDisplay();
//		} catch (SQLException | DaoException e1) {
//			reportProblem(response, "tres grave erreur de SQL !");
//			e1.printStackTrace();
//		}

	}

	/*
	 * This method is going to validate the parameters coming from the form. it
	 * will provide a list
	 */
	private StockPass initParameters(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, DaoException {
		StockPass stockPack = new StockPass();
		String[] formValues = request.getParameterValues("form");
		String[] priceValues = request.getParameterValues("price");

		int p = 0;

		// System.out.println("--- Affichage des quantités ---");

		if(isFormCorrect(formValues, priceValues)) {
			stockPack = new StockPass(prix[0], prix[1], prix[2], dayQuantity[0], dayQuantity[1], dayQuantity[2],
					dayQuantity[3], dayQuantity[4], dayQuantity[5]);
			stockPack.generateTickets(false);
			url = "/WEB-INF/pass/gestion.jsp";
		}else {
			System.out.println("there is an issue in the form...");
			url = "/WEB-INF/gestion.jsp";
			stockPack = new StockPass();

			
		}
		return stockPack;
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
				System.out.println(prix1 + " " + prix2+ " " + prix3);

				zero = Integer.parseInt(formValues[0]);
				one = Integer.parseInt(formValues[1]);
				two = Integer.parseInt(formValues[2]);
				three = Integer.parseInt(formValues[3]);
				four = Integer.parseInt(formValues[4]);
				five = Integer.parseInt(formValues[5]);
				System.out.println(formValues[3] + " " + formValues[4]+ " " + formValues[5]);
			} catch (NumberFormatException n) {
				System.out.println("one of the paraméter is not a number or field is empty !");
				return false;

			}
			/*
			 * if(prix1 == 0 || prix2 == 0 || prix3 == 0 || zero == 0 || one ==
			 * 0 || two == 0 || three == 0 || four == 0 || five == 0 ) {
			 * System.out.println(
			 * "---MANAGEPASS--- il ya une ou plusieurs valeurs manquantes.---"
			 * );
			 * 
			 * url = "/gestionErreur/Error";
			 * 
			 * 
			 * }
			 */

			if (prix1 == 0 && prix2 == 0 && prix3 == 0 && zero == 0 && one == 0 && two == 0 && three == 0 && four == 0
					&& five == 0) {

				// TODO (nicolas) line to be removed for now
				// stockPack.generateTickets(true);
				System.out.println("---MANAGEPASS--- tous les paramètres sont a 0; lancement du generateur ---");
				//url = "/WEB-INF/gestion.jsp";
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
								"---MANAGEPASS--- journée dépasse le nombre max de place (1000) ou infèrieur a 0 à position : "+ i);
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

				if(prix1 <= 0 || prix2 <= 0 || prix3 <= 0) {
					System.out.println("---MANAGEPASS--- un des prix est infèrieur a 0");
					return false;
				}
				

				if ((prix1 >= prix2) || (prix1 >= prix3)) {
					System.out.println(
							"---MANAGEPASS--- Prix d'une place ne peut-être supèrieur au prix de 2 ou 3 jours.");
					System.out.println(prix1 +" >= "+ prix2 +" || " + prix1 + " >= " + prix2 );
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
	private void dispatchToGestion(HttpServletRequest req, HttpServletResponse res, StockPass stockPack, String uri)
			throws IOException, ServletException, DaoException {
		
		if(uri.equals("/gestionErreur/Error")) {
			RequestDispatcher rd = req.getRequestDispatcher(uri);
			rd.forward(req, res);
		} else {
			
		
		
		
		Passes lesStock = null;
		try {
			lesStock = stockPack.getAllDBPass();
		} catch (SQLException e) {
			reportProblem(res, "Probleme SQL " + e.getMessage());
			e.printStackTrace();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      req)
	 */
	/*
	 * protected void doPostOld(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * Enumeration paramNames = request.getParameterNames(); int twoDayPrice =
	 * 0; float twoDayRate = 0; int threeDayPrice = 0; float threeDayRate = 0;
	 * 
	 * String[] paramValues = request.getParameterValues("ticket"); if
	 * (paramValues == null) { System.out.println("paramvalue is null");
	 * reportProblem(response, "paramvalue  is null "); }
	 * System.out.println(paramValues.length);
	 * System.out.println(paramValues[0]); System.out.println(paramValues[1]);
	 * System.out.println(paramValues[2]); System.out.println(paramValues[3]);
	 * 
	 * if (paramValues[0].equals("") && paramValues[1].equals("") &&
	 * paramValues[2].equals("") && paramValues[3].equals("")) {
	 * System.out.println("all the fields are empty"); reportProblem(response,
	 * "HOLY COW !!! please make sure you insert price or ratio for tickets");
	 * return; } else if (!paramValues[0].equals("") &&
	 * !paramValues[2].equals("") && paramValues[1].equals("") &&
	 * paramValues[3].equals("")) { StockPass stock = new StockPass(); Pass
	 * pass1 = stock.getAPass(1); if (Integer.parseInt(paramValues[0]) <=
	 * pass1.getPrice() || Integer.parseInt(paramValues[2]) <= pass1.getPrice())
	 * { System.out.println(
	 * "Warning Price cannot be lower than the 1 day passes");
	 * request.setAttribute("alertMsg",
	 * "Warning Price cannot be lower than the 1 day passes"); RequestDispatcher
	 * rd = request.getRequestDispatcher("/WEB-INF/gestion.jsp");
	 * rd.include(request, response); return;
	 * 
	 * } else {
	 * 
	 * Pass pass3 = stock.getAPass(3); Pass pass5 = stock.getAPass(5); Pass
	 * pass6 = stock.getAPass(6);
	 * 
	 * pass3.setPrice(Integer.parseInt(paramValues[0]));
	 * pass5.setPrice(Integer.parseInt(paramValues[0]));
	 * pass6.setPrice(Integer.parseInt(paramValues[2]));
	 * 
	 * stock.updatePrice(pass3); stock.updatePrice(pass5);
	 * stock.updatePrice(pass6);
	 * 
	 * System.out.printf("modification du prix %s et %s", paramValues[0],
	 * paramValues[2]);
	 * 
	 * ArrayList<Pass> lesStock = null; try { lesStock = stock.getAllDBPass(); }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } try { stock.niceDisplay(); } catch (SQLException
	 * e) { reportProblem(response, "tres grave erreur de SQL !");
	 * e.printStackTrace(); }
	 * 
	 * System.out.println("prepare to display on html...");
	 * request.setAttribute("allRow", lesStock);
	 * response.setContentType("text/html"); response.setHeader("Cache-Control",
	 * "no-cache");
	 * 
	 * RequestDispatcher rd =
	 * request.getRequestDispatcher("/WEB-INF/gestion.jsp"); rd.forward(request,
	 * response); return; }
	 * 
	 * } else {
	 * 
	 * 
	 * 
	 * twoDayPrice = Integer.parseInt(paramValues[0]);
	 * 
	 * twoDayRate = Float.parseFloat(paramValues[1]);
	 * 
	 * threeDayPrice = Integer.parseInt(paramValues[2]);
	 * 
	 * threeDayRate = Float.parseFloat(paramValues[3]);
	 * 
	 * System.out.printf("données %s, %s, %s, %s", twoDayPrice, twoDayRate,
	 * threeDayPrice, threeDayRate);
	 * 
	 * } float limite = 0; if (twoDayRate > 0.4) { System.out.println(
	 * "incorrecte valeur... doit etre inferieure a 40%");
	 * reportProblem(response,
	 * "HOLY COW !!! please make sure you insert ratio for tickets is inferior at 40%"
	 * ); return; } else { float reste = (float) (1.00 - (twoDayRate * 2));
	 * limite = (float) (reste - 0.10); }
	 * 
	 * if (threeDayRate >= limite) { System.out.println("incorrecte valeur...");
	 * reportProblem(response,
	 * "HOLY COW !!! please make sure you insert ratio for tickets"); return;
	 * 
	 * } else { System.out.println("2 days rate is " + twoDayRate +
	 * "3 days rate is " + threeDayRate); }
	 * 
	 * StockPass stock = new StockPass(twoDayRate, threeDayRate, twoDayPrice,
	 * threeDayPrice); stock.generateTickets();
	 * 
	 * stock.emptyTable(); stock.storeInDatabase();
	 * 
	 * try { stock.niceDisplay(); } catch (SQLException e1) {
	 * reportProblem(response, "tres grave erreur de SQL !");
	 * e1.printStackTrace(); }
	 * 
	 * ArrayList<Pass> lesStock = null; try { lesStock = stock.getAllDBPass(); }
	 * catch (SQLException e) { reportProblem(response, "Probleme SQL " +
	 * e.getMessage()); e.printStackTrace(); }
	 * 
	 * System.out.printf("lesStock" + lesStock.toString());
	 * 
	 * System.out.println("prepare to display on html...");
	 * request.setAttribute("allRow", lesStock);
	 * response.setContentType("text/html"); response.setHeader("Cache-Control",
	 * "no-cache");
	 * 
	 * RequestDispatcher rd =
	 * request.getRequestDispatcher("/WEB-INF/gestion.jsp"); rd.forward(request,
	 * response);
	 * 
	 * }
	 */

	private void reportProblem(HttpServletResponse resp, String message) throws IOException {
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
	}
}
