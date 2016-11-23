package fr.cdiFestival.servlet.pass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * This Servlet is going to displqy all the created pass that need to be displayed to the user.
 * 
 * Get : is going to dispatch to the consult.jsp view (defaut behavior)
 * when user select a pass it is going to dispatch to the Checkout servlet.
 * 
 * @author Nicolas Tarral
 * @version 2016-11-23
 */
@WebServlet(
		name = "ShowCase", 
		description = "Controleur Show", 
		urlPatterns = {"/consult/ControlShowCase/*"}
		)
public class ShowCase extends HttpServlet {
	
	private String url;
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCase() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	url = "/WEB-INF/pass/consult.jsp";
    }


    
    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" -- ShowCase -- in the GET method");
		
		StockPass stock 	= new StockPass();
		boolean listFull 	= false;
		String id 			= request.getParameter("id");
		int identifier 		= 0;
		Boolean isPass		= false;
		
		// ce block est apell� des la selection d'un des pass a acheter. 
		
		if(id != null) {
			
			try {
				identifier = Integer.parseInt(id);
			}catch (NumberFormatException e) {
				url = "gestionErreur/error";
				
				//reportProblem(response, "incorrect parameters, please insert digit");
				//return;
			}

			if(identifier >= 0 && identifier <7) {
				Pass pass = new Pass();
				System.out.println("the type of passes is "+identifier);
				pass = stock.getAPass(identifier);
				System.out.println(pass);
				
				request.setAttribute("selectedPass", pass);

				// TODO (nicolas) stoocker url aux meme endroit class propriete static...?
				url = "/consult/ControlShowCase/checkout/Checkout";
			}else {
				url = "gestionErreur/error";
				//reportProblem(response, "incorrect parameters, doit etre compris entre 0 et 6");
				//return;
			}
			
			// Ce block est apelle lorsque la page est charg�e.	
		}else {
			ArrayList<Pass> lesStock = null;
			try {
				lesStock = stock.getAllDBPass();
			} catch (SQLException | DaoException e) {
				System.out.println("erreur sql "+e.getMessage());
				url = "gestionErreur/error";
				//reportProblem(response, "Erreur de connection � la base de donn�e.");
			}
			if(lesStock.isEmpty()) {
				System.out.println("il n'y a pas de ticket en vente pour le moment.");
				listFull = false;
				
				
			}else {
				listFull = true;
				System.out.println("prepare to display on html...");
				
			}
			request.setAttribute("listAvail",listFull);
			request.setAttribute("allPasses", lesStock);
			url = "/WEB-INF/pass/consult.jsp";
		}
		
		
		
		
		// ce block de code va faire un forward vers la ressource determine par url
		response.setContentType("text/html");
		response.setHeader("Cache-Control","no-cache");
		RequestDispatcher rd=request.getRequestDispatcher(url);  
        rd.forward(request, response);

	}
	




}
