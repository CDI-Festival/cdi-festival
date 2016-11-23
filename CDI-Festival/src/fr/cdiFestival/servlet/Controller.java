package fr.cdiFestival.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.article.RequestArticle;

/**
 * Main controller
 * Servlet implementation class Controller
 * 
 * @author Jonathan, Claire, Nicolas, Jean-Luc
 */
@WebServlet(
		name = "Controller", 
		description = "Main controller", 
		urlPatterns = {"/accueil/*"}
		)

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	private	String			  path;	
	private	RequestArticle	  reqArticle;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        System.out.println(" Main Controller");
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		
		if (path == null) {
			goIndex(request, response);
		}
		
		switch (path) {
		
		case "/":
			goIndex(request, response);
			break;
			
		case "/groupe":
			response.sendRedirect(request.getContextPath() + "/groupes");
			break;

		case "/pass":
			response.sendRedirect(request.getContextPath() + "/pass");
			break;
		default:
			goIndex(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Tentative de modification d'URL côté client");
	}
	

	/**
	 * Open index page with article listing
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @author Jonathan Fuentes
	 */
	public void goIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle = new RequestArticle();
		
		request.setAttribute("articles", reqArticle.getArticles());
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	
		System.out.println("Methode doIndex");
	}
}
