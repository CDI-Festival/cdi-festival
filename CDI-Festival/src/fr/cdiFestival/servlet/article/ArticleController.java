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
import fr.cdiFestival.model.Article;
import fr.cdiFestival.service.Articles;

/**
 * Servlet implementation class ControllerNews
 */
@WebServlet(name 		= "Controller", 
			description = "Article controller", 
			urlPatterns = {"/article/*"})

public class ArticleController extends HttpServlet {
	
	private static final long 	serialVersionUID = 1L;
	
	private	RequestDispatcher	dispatcher;
	private	String				path;
	
	private	Article				article;
	private Articles			listArticle;
	private	RequestArticle		reqArticle;
	private	int					id;
	private String				author;
	private String				date;
	private	String				title; 
	private String				content;
	
	private	DateTimeFormatter 	format;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		
		System.out.println("ArticleController doGet path=" + path );
		System.out.println("ArticleController doGet path contexte =" + request.getContextPath() );
		
		if (path == null || path.equals("/")) this.goIndex(request, response);
		if (path.equals("/read")) this.read(request, response);
		if (path.equals("/updatepage")) this.goUpDatePage(request, response);
		if (path.equals("/addpage")) this.goAddPage(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getPathInfo();
		System.out.println("ArticleController doPost path=" + path );
		System.out.println("ArticleController doPost path contexte =" + request.getContextPath() );
		
		if (path.equals("/add")) this.add(request, response);
		if (path.equals("/update")) this.upDate(request, response);
	}
	
	
	
	
	
	// Public index page method to display all articles
	public void goIndex (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle = new RequestArticle();
		listArticle = null;
		
		request.setAttribute("articles", reqArticle.getArticles());
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	//Get the article onClick and open a new page to read it
	public void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		id 			= Integer.parseInt(request.getParameter("id").trim());
		article 	= reqArticle.getArticle(id);
			
		request.setAttribute( "article", article );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/article/read.jsp" ).forward( request, response );	
	}
	
	//Open the article maker page
	public void goAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("http://localhost:8085/CDI_Festival/view/article/createArticle.html");
		System.out.println("Methode goAddPage");
	}
	
	//add a new article in the dataBase and redirect to index.jsp
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle  = new RequestArticle();
		format		= DateTimeFormatter.ofPattern("dd/MM/uuuu");
		
		author		= request.getParameter("author");
		title		= request.getParameter("title");
		date		= (LocalDate.now().format(format));
		content		= request.getParameter("content");
		
		article		= new Article(author, date, title, content);
		
		reqArticle.add(article);
		
		this.goIndex(request, response);
	}
	
	//Open the update page
	public void goUpDatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqArticle 	= new RequestArticle();
		id 			= Integer.parseInt(request.getParameter("id").trim());
		article 	= reqArticle.getArticle(id);
				
		request.setAttribute( "article", article );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/article/update.jsp" ).forward( request, response );	
	}
	
	//update an article and redirect to index.jsp
	public void upDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}
	
//	// Redirect into ArticleController
//	public void goArticleManage (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		dispatcher = request.getRequestDispatcher("/article/" + path);
//		dispatcher.forward(request,response);	
//		System.out.println("Methode goArticleController");
//	}
}