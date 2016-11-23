package fr.cdiFestival.servlet.pass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.model.Pass;
import fr.cdiFestival.technic.StockPass;


/**
 * Ce servlet va afficher le pass sélectionné par l'utilisateur, en le renvoyant vers cart.jsp
 * 
 * 
 * en POST le servlet va vérifier si les places sont disponible et mettre a jour dans la base de données les places disponibles.
 * dans le cas contraire il renverra vers cartVoid.html.
 */

@WebServlet(
		name = "Checkout", 
		description = "Controleur checkout", 
		urlPatterns = {"/consult/ControlShowCase/checkout/*"}
		)
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Pass passInCart;  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		passInCart = (Pass)  request.getAttribute("selectedPass");
		
		
		request.setAttribute("price", passInCart.getPrice());
		request.setAttribute("description", passInCart.getDayType());
		request.setAttribute("date", passInCart.getDate());
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control","no-cache");
		RequestDispatcher passDispatch =request.getRequestDispatcher("/WEB-INF/cart.jsp");  
		passDispatch.forward(request, response);
        return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(" --- in do post ---");
		String option = (String) request.getParameter("ticketQuantity");
		Enumeration paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) {
	      String paramName = (String)paramNames.nextElement();

	      String[] paramValues =
	        request.getParameterValues(paramName);
	      System.out.println(paramValues[0]);
	      
	    }

		
		System.out.println("valeur de car "+option);
		int passQuantity = Integer.parseInt(option);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control","no-cache");
		int rest = passInCart.getNombre() - passQuantity;
		if(rest >= 0) {
			passInCart.setNombre(rest);
			StockPass stock = new StockPass();
			stock.updateQuantity(passInCart);
			System.out.println(request.getServletContext());
			System.out.println(request.getContextPath());
			System.out.println(request.getServletPath());
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}else {
			System.out.println("not enough tickets");
			System.out.println(request.getServletContext());
			System.out.println(request.getContextPath());
			System.out.println(request.getServletPath());
			RequestDispatcher passDispatch =request.getRequestDispatcher("/WEB-INF/cartOk.html");  
			passDispatch.forward(request, response);

		}
		
		

		
//		try {
//			stock.niceDisplay();
//		} catch (SQLException | DaoException e) {
//			reportProblem(response, "tres grave erreur de SQL !");
//			e.printStackTrace();
//		}
//		
		
		

		//response.sendRedirect("../../../main.jsp");
		
		//System.out.println("--- prix total = "+passInCart.getPrice() * nbTicket);
	}
	
	private void reportProblem(HttpServletResponse response, String message) throws IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
	}

}
