package fr.cdiFestival.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dal.article.RequestArticle;
import fr.cdiFestival.service.Articles;

/**
 * Main controller
 * Servlet implementation class Controller
 * 
 * @author Jonathan / Claire / Nicolas
 */
@WebServlet(
		name = "Controleur", 
		description = "Controleur General", 
		urlPatterns = {"/accueil/*"}
		)

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private	String			  path;
	private	RequestArticle	reqArticle;
	private Articles		listArticle;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();

		if (path == null) {
			System.out.println("path est null "+path);
			goIndex(request, response);
		}
		else {
			
			switch (path) {
			
			case "/":
				goIndex(request, response);
				System.out.println("in the /");
				break;
				
			case "/groupes":
				response.sendRedirect(request.getContextPath() + "/groupes");
				break;

			case "/pass":
				System.out.println("APRES PATH: PASS");
				response.sendRedirect(request.getContextPath() + "/pass");
				break;
			default:
				System.out.println("aller dans goIndex...");
				goIndex(request, response);
			}
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Méthode doPost");
	}
	

	/**
	 * Open the index page and display a article list
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		listArticle	= reqArticle.getListArticle();

		if (listArticle != null) {
			request.setAttribute("articles", listArticle);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		else {
			System.out.println("Générer un HTML sans article");
		}
		

	}
}
