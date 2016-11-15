package fr.cdiFestival.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiFestival.model.Band;

/**
 * Handles informations from the HTML band creation form.
 * 
 * @author Claire
 * @version 20161115
 */
@WebServlet("/CreateBand")
public class CreateBand extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	// Attributes to create a new band.
	private Band band;
	private String name;
	private String biography;
	private String discography;
	private String website;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBand() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TEST CODE
		PrintWriter out = response.getWriter();
		
		name = request.getParameter("bandname");
		biography = request.getParameter("bandbio");
		discography = request.getParameter("banddisco");
		website = request.getParameter("bandwebsite");
		
		// TODO (Claire) controls data + error display or use JS?
		
		band = new Band(name, biography, discography, website);
		
		// TEST CODE
		out.println(band);
	}

}
