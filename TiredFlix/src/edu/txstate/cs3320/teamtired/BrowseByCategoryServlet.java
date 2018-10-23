package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.film.FilmCategory;
import edu.txstate.cs3320.teamtired.helper.FilmFactory;

/**
 * Servlet implementation class BrowseByCategoryServlet
 */
@WebServlet("/BrowseByCategoryServlet")
public class BrowseByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseByCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryString = request.getParameter("Category");
		
		FilmCategory category = FilmFactory.convertCategory(categoryString);
		
		List<Film> films = FilmDAO.findFilmsByCategory(category);
		
		request.setAttribute("detailServlet", "/BrowseByCategoryServlet");
		
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
