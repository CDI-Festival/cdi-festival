package fr.cdiFestival.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
	name 		= "Controller", 
	description = "Main controller", 
	urlPatterns = {"/Festival/*"}
)

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private	RequestDispatcher dispatcher;
	private	String			  path;
	
	private	Article			article;
	private Articles		listArticle;
	private	RequestArticle	reqArticle;
	private	int				id;
	private String			author;
	private String			date;
	private	String			title; 
	private String			content;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        System.out.println("COntroller init");
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		
		System.out.println("================  dans Controleur path=" + path );
		System.out.println("================  dans Controleur path contexte =" + request.getContextPath() );
		
		if (path == null || path.equals("/")) goIndex(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Méthode doPost");
	}
	
	
	// Public index page method to display all the articles
	public void goIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listArticle = reqArticle.getArticles();
		
		request.setAttribute("articles", listArticle);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	
		System.out.println("Methode doIndex");
	}
}
