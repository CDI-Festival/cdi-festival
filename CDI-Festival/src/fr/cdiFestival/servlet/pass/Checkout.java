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

import fr.cdiFestival.dao.pass.DaoException;
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
		urlPatterns = {"/order/*"}
		)
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Pass passInCart; 
	private String url;
	private StockPass stock;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		passInCart = (Pass)  request.getAttribute("selectedPass");
		url = "/WEB-INF/pass/cart.jsp";
		
		request.setAttribute("price", passInCart.getPrice());
		request.setAttribute("description", passInCart.getDayType());
		request.setAttribute("date", passInCart.getDate());
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control","no-cache");
		RequestDispatcher passDispatch =request.getRequestDispatcher(url);  
		passDispatch.forward(request, response);
        return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		stock = (StockPass) getServletContext().getAttribute("stock");
		url = "/WEB-INF/pass/voidCart.html";
		System.out.println(" --- in do post ---");
		String option = (String) request.getParameter("ticketQuantity");
	

		System.out.println("valeur de car "+option);
		int passQuantity = Integer.parseInt(option);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control","no-cache");
		int rest = passInCart.getNombre() - passQuantity;
		if(rest >= 0) {
			passInCart.setNombre(rest);

			
			try {
				stock.updateQuantity(passInCart);
			} catch (DaoException e) {
				System.out.println("erreur SQL dans Manage Pass");
				request.setAttribute("error", e.getMessage());
				url = "/gestionErreur/sql";
			}
			
			System.out.println(request.getServletContext());
			System.out.println(request.getContextPath());
			System.out.println(request.getServletPath());
			response.sendRedirect(request.getContextPath() + "accueil/");
		}else {
			System.out.println("not enough tickets");
			System.out.println(request.getServletContext());
			System.out.println(request.getContextPath());
			System.out.println(request.getServletPath());
			RequestDispatcher passDispatch =request.getRequestDispatcher(url);  
			passDispatch.forward(request, response);

		}

	}
	

}
