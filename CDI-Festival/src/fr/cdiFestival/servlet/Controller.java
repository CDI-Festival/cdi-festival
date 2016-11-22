package fr.cdiFestival.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.service.Articles;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
		name = "Controleur", 
		description = "Controleur General", 
		urlPatterns = {"/accueil/*"}
		)

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private	RequestDispatcher dispatcher;
	private	String			  path;
	
	private Articles		listArticle;
	private	RequestArticle	reqArticle;

	
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
		
		System.out.println("Controller path=" + path );
		System.out.println("Controller path2=" + path );
		System.out.println("Controller path contexte =" + request.getContextPath() );
		
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
		System.out.println("Méthode doPost");
	}
	
	
	// Public index page method to display all articles
	public void goIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle = new RequestArticle();
		listArticle = null;
		
		request.setAttribute("articles", reqArticle.getArticles());
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	
		System.out.println("Methode doIndex");
	}
}
