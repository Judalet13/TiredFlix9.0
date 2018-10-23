package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.film.Film;

/**
 * Servlet implementation class SplashPage
 */
@WebServlet("/index")
public class SplashPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int MAX_FILMS_ON_LOAD = 8;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SplashPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Film> films = FilmDAO.findNewestFilms(MAX_FILMS_ON_LOAD); 
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
			session.setAttribute("userId", "guest");
			List<Integer> cart = new ArrayList<Integer>();
			session.setAttribute("cart", cart);
		}
		
		request.setAttribute("detailServlet", "CyberFlixMovieDetailServlet");
		
		request.setAttribute("films", films);
		request.setAttribute("invalidUsername", false);
		request.setAttribute("invalidPassword", false);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
