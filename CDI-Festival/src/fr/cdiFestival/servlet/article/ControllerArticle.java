package fr.cdiFestival.servlet.article;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.model.Article;

/**
 * Servlet implementation class ControllerNews
 */
@WebServlet(name = "Controller", 
		description = "Article controller", 
		urlPatterns = {"/Article/*"})

public class ControllerArticle extends HttpServlet {
	
	private static final long 				serialVersionUID = 1L;
	private				 RequestDispatcher	dispatcher;
	private				 String				path;
	
	private				 Article			article;
	private				 RequestArticle		reqArticle;
	private				 int				id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerArticle() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		
		System.out.println("================  dans Controleur path=" + path );
		System.out.println("================  dans Controleur path contexte =" + request.getContextPath() );
		
		if (path.equals("/read")) goRead(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//
	public void goIndexAdm (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("/indexAdm.jsp");
		dispatcher.forward(request,response);
		System.out.println("Methode goIndexAdm");
	}
	
	public void goRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("cote"));
		
		article = reqArticle.getArticle(id);
		
		request.setAttribute( "article", article );
		this.getServletContext().getRequestDispatcher( "WEB-INF/article/read.jsp" ).forward( request, response );
		System.out.println("Methode goRead");
		
	}
	
}