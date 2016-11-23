package fr.cdiFestival.servlet.article;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dao.article.RequestArticle;
import fr.cdiFestival.model.Article;

/**
 * ArticleController is used to handle all the article actions in the webSite.
 *Centralize and dispatch directly in the appropriated method and GUI
 *
 * @see Article

 * Servlet implementation class ControllerNews
 * 
 * @author Jonathan Fuentes
 * @version 22/11/2012
 */
@WebServlet(name 		= "Controller", 
			description = "Article controller", 
			urlPatterns = {"/article/*"})


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

		//Check the path value and redirecting to the appropriate method
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

		//Check the path value and redirecting to the appropriate method
		if (path.equals("/add")) this.add(request, response);
		if (path.equals("/update")) this.upDate(request, response);
		if (path.equals("/delete")) this.delete(request, response);
	}
	
	
	//Methods to handle article actions
	
	/**
	 * Open the reading page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		
		//Getting ID from the .jsp 
		id 	= Integer.parseInt(request.getParameter("id").trim());
		
		//Checking and making request to getting the good article in database
		if (id !=0) article = reqArticle.getArticle(id);
			
		//Passing the article found into the JSP to display it into the good .jsp
		request.setAttribute( "article", article );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/article/read.jsp" ).forward( request, response );	
	}
	
	/**
	 * Open the adding page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void goAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Just redirect into the creation form
		response.sendRedirect("http://localhost:8085/CDI_Festival/view/article/createArticle.html");
	}
	
	
	/**
	 * Get all the form informations and add a new article in the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initializing useful attributes
		reqArticle  = new RequestArticle();
		format		= DateTimeFormatter.ofPattern("dd/MM/uuuu");
		
		//Getting and checking values from the form and initialize date
		if (request.getParameter("author")  != null) author  = request.getParameter("author");
		if (request.getParameter("title")   != null) title   = request.getParameter("title");
		if (request.getParameter("content") != null) content = request.getParameter("content");
		
		date = (LocalDate.now().format(format));
		
		//Second check to be sure and making article instance
		if ((author != null) && (title != null) && (content != null)) article = new Article(author, date, title, content);
		
		//Making request to add the article into database
		reqArticle.add(article);
		
		//Redirect into index page
		this.redirIndex(request, response);
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
		
		//Getting ID from the .jsp
		id = Integer.parseInt(request.getParameter("id").trim());
		
		//Checking and making request to match the good article in database
		if (id != 0) article = reqArticle.getArticle(id);
				
		//Passing the article found into the JSP to display it into the good .jsp and update it
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
		
		//Getting and checking values from the form and initialize date
		if (request.getParameter("author")  != null) author  = request.getParameter("author");
		if (request.getParameter("title")   != null) title   = request.getParameter("title");
		if (request.getParameter("content") != null) content = request.getParameter("content");
				
		date = (LocalDate.now().format(format));
				
		//Second check to be sure and making article instance
		if ((author != null) && (title != null) && (content != null)) article = new Article(author, date, title, content);
		
		//Making request to update database
		reqArticle.add(article);
		
		//Send to index page
		this.redirIndex(request, response);
		}
	

	/**
	 * Erase the article in the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		
		//Getting and checking ID value
		if (request.getParameter("hiddenid") != null) id = Integer.parseInt(request.getParameter("hiddenid"));
		
		//Making deletion request
		reqArticle.delete(id);
	}
	
	/**
	 * Redirect to index page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void redirIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/accueil");
	}
	
}