package fr.cdiFestival.servlet.article;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.dal.article.RequestArticle;
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
	private void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	private void goAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Just redirect into the creation form
		response.sendRedirect("http://localhost:8085/CDI_Festival/view/article/createArticle.html");
	}
	
	
	/**
	 * Get all the form informations and add a new article in the database
	 * @param request
	 * @param response
	 * @return 
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initializing useful attributes
		System.out.println("add mehtode");
		reqArticle  = new RequestArticle();
		format		= DateTimeFormatter.ofPattern("dd/MM/uuuu");
		
		//Encoding informations in UTF-8 (Thank you Dominique)
		request.setCharacterEncoding("UTF-8");

		//Getting and checking values from the form and initialize date
		if ((request.getParameter("author").isEmpty()  != true) && (request.getParameter("title").isEmpty()  != true) 
				&& (request.getParameter("content").isEmpty() != true)) {
			
			author  = request.getParameter("author").trim();
			title   = request.getParameter("title").trim();
			content = request.getParameter("content").trim();

			date = (LocalDate.now().format(format));
		
			article = new Article(author, date, title, content);
		
			//Making request to add the article into database
			reqArticle.add(article);
		
			//Redirect into index page
			this.redirIndex(request, response);
		}
		else {
			this.goAddPage(request, response);
		}
		
	}
	
	
	/**
	 * Open the update page.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goUpDatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	private void upDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initializing useful attributes
		reqArticle  = new RequestArticle();
		format		= DateTimeFormatter.ofPattern("dd/MM/uuuu");
				
		//Encoding informations in UTF-8 (Thank you Dominique)
		request.setCharacterEncoding("UTF-8");

		//Getting and checking values from the form and initialize date
		if ((request.getParameter("hiddenid") != null) && (request.getParameter("author").isEmpty()  != true) && (request.getParameter("title").isEmpty()  != true) 
				&& (request.getParameter("content").isEmpty() != true)) {
			
			id = Integer.parseInt(request.getParameter("hiddenid"));	
			author  = request.getParameter("author").trim();
			title   = request.getParameter("title").trim();
			content = request.getParameter("content").trim();

			date = (LocalDate.now().format(format));
			
			article = new Article(id, author, date, title, content);

			//Making request to add the article into database
			reqArticle.upDate(article);
				
			//Redirect into index page
			this.redirIndex(request, response);
		}
		
		else {
			this.goAddPage(request, response); //I'm sure I can do better than this with a little more time (or maybe not)
		}
	}
	

	/**
	 * Erase the article in the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		
		//Getting and checking ID value
		if (request.getParameter("hiddenid") != null) id = Integer.parseInt(request.getParameter("hiddenid"));
		
		//Making deletion request
		reqArticle.delete(id);
		
		//Redirect into index page
		this.redirIndex(request, response);
	}
	
	/**
	 * Redirects to index page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void redirIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/accueil");
	}
	
}