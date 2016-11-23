package fr.cdiFestival.servlet.article;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.RequestArticle;
import fr.cdiFestival.dao.RequestId;
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

/**
 * Servlet implementation class ControllerNews
 */
@WebServlet(name 		= "Controller", 
			description = "Article controller", 
			urlPatterns = {"/article/*"})

/**
 *ArticleCOntroller use to handle all the article actions in the webSite.
 *centralized, and dispatch directly in the appropriated method and GUI
 * @see Article
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
public class ArticleController extends HttpServlet {
	
	//Class attributes
	private static final long 	serialVersionUID = 1L;
	
	private	String				path;
	
	private	Article				article;
	private	int					id;
	private String				author;
	private String				date;
	private	String				title; 
	private String				content;
	private	RequestArticle		reqArticle;
	private RequestId			reqId;
	
	private	DateTimeFormatter 	format;
       
    /**
     * Constructor
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();

    }

	/**
	 * doGet method is use to handle all action without database manipulations
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		
		System.out.println("ArticleController doGet path=" + path );
		System.out.println("ArticleController doGet path contexte =" + request.getContextPath() );
		
		if (path.equals("/read")) this.read(request, response);
		if (path.equals("/updatepage")) this.goUpDatePage(request, response);
		if (path.equals("/addpage")) this.goAddPage(request, response);
	}


	/**
	 * doPost method is safer. Used to handle all updating in database.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		System.out.println("ArticleController doPost path=" + path );
		System.out.println("ArticleController doPost path contexte =" + request.getContextPath() );
		
		if (path.equals("/add")) this.add(request, response);
		if (path.equals("/update")) this.upDate(request, response);
		if (path.equals("/delete")) this.delete(request, response);
	}
	
	
	//*******************************************//
	//					Méthods					 //
	//*******************************************//
	
	/**
	 * Open the reading page article
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		id 			= Integer.parseInt(request.getParameter("id").trim());
		article 	= reqArticle.getArticle(id);
			
		request.setAttribute( "article", article );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/article/read.jsp" ).forward( request, response );	
	}
	
	/**
	 * Open the adding page article
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void goAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("http://localhost:8085/CDI_Festival/view/article/createArticle.html");
		System.out.println("Methode goAddPage");
	}
	
	/**
	 * Get all the form informations and add a new article in the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle  = new RequestArticle();
		reqId		= new RequestId();
		format		= DateTimeFormatter.ofPattern("dd/MM/uuuu");
		
		author		= request.getParameter("author");
		title		= request.getParameter("title");
		date		= (LocalDate.now().format(format));
		content		= request.getParameter("content");
		
		article		= new Article(author, date, title, content);
		
		reqArticle.add(article);
		
		reqId.update(article.getId());
		
		this.goAccueil(request, response);
	}
	
	/**
	 * Open the update page.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void goUpDatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		id 			= Integer.parseInt(request.getParameter("id").trim());
		article 	= reqArticle.getArticle(id);
				
		request.setAttribute( "article", article );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/article/update.jsp" ).forward( request, response );	
	}
	
	/**
	 * Get all the form informations and update the article in the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void upDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle  = new RequestArticle();
		format		= DateTimeFormatter.ofPattern("dd/MM/uuuu");
		
		author		= request.getParameter("author");
		title		= request.getParameter("title");
		date		= (LocalDate.now().format(format));
		content		= request.getParameter("content");
		
		article		= new Article(author, date, title, content);
		
		reqArticle.upDate(article);
		}
	

	/**
	 * Get the id and erase the article in the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		id = Integer.parseInt(request.getParameter("hiddenid"));

		reqArticle.delete(id);
	}
	
	public void goAccueil (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/accueil");
	}
	
}