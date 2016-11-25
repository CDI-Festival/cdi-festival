package fr.cdiFestival.control.pass;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ce servlet va servir à centraliser les erreurs provenant des autres servlet, et de rediriger vers un message d'erreur.
 */
@WebServlet(
		name = "ErrorServlet", 
		description = "Controleur General", 
		urlPatterns = {"/gestionErreur/*"}
		)
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		if(path.equals("/sql")) {
			String err = (String) request.getAttribute("error");
			System.out.println("--- dans error servlet ---");
			reportProblem(response, "VERY BIG PROBLEMS " + err);
		}else {
			
		}

		
		
	}
	/*
	 * Cette methode va envoyé l'utilisateur vers une page d'erreur.
	 */
	private void reportProblem(HttpServletResponse resp, String message) throws IOException {
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
		return;
	}
	
}
