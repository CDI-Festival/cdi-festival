package fr.cdiFestival.control.pass;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.technic.StockPass;



/**
 * 
 * Main controller for pass managment, it is going to dispatch user to  ShowCase servlet to displqy pass, or ManagePass servlet to manage all the pass. 
 * or to the index.jsp.
 * It is going to creqte StockPass object and qssign it to the application context.
 * 
 * @author Nicolas Tarral
 * @version 2016-11-22
 */
@WebServlet(
		name = "MainServlet", 
		description = "Controleur General", 
		urlPatterns = {"/pass/*"}
		)
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;
       
	public void init() {
		StockPass appStock = new StockPass();
		application = getServletContext();
		application.setAttribute("stock", appStock);
	}


    /**
     * This class is going to receive different path request and will address any corresponding servlet.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url  = null;
	
		System.out.println(request.getServletPath());
		System.out.println(request.getContextPath());
		System.out.println(request.getServletContext());
		
		String path = request.getPathInfo();
		System.out.println("--- MAINSERVLET doGet ---");
		System.out.println("le path requis est "+path);
		
		if (path == null || path.equals("/")) {
			System.out.println("Redirect to main page");
			url = "/index.jsp";
		} else if (path.equals("/show")) {
			System.out.println("Redirect to the checkout page");
			url = "/consult/ControlShowCase/ShowCase";
		}else if (path.equals("/manage")){
			System.out.println("Redirect to passes managment");
			url = "/ControlManager/ManagePass";
		} else {
			System.out.println("SendRedirect to the main "+request.getContextPath() + "/");
			//response.sendRedirect(request.getContextPath() + "/main.html");
			url = "/";

		}
		
		// the request dispatcher used for any forward request.
		
		RequestDispatcher reqDIs = this.getServletContext().getRequestDispatcher( url );
		reqDIs.forward(request, response);
		
		
	}

	
}
