package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.helper.FilmFactory;

/**
 * Servlet implementation class CyberFlixServlet
 */
@WebServlet("/CyberFlixServlet")
public class CyberFlixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTitle = request.getParameter("film_title");
		String searchDescription = request.getParameter("film_description");
		Integer length = Integer.parseInt(request.getParameter("film_length")); 
		Film.FilmRating rating = FilmFactory.convert(request.getParameter("film_rating"));
		
		List<Film> films = FilmDAO.findFilmsByAttributes(searchTitle, searchDescription, length, rating);
		
		request.setAttribute("detailServlet", "CyberFlixMovieDetailServlet");
		
		request.setAttribute("films", films);
		
		request.getRequestDispatcher("moviesearchresults.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
